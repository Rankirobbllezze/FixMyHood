package com.alenga.fixmyhood.data.repository


import android.net.Uri
import android.util.Log
import com.alenga.fixmyhood.data.dao.ChallengeDao
import com.alenga.fixmyhood.data.local.ChallengeEntity
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
import com.alenga.fixmyhood.data.repository.ChallengeRepository
import com.alenga.fixmyhood.data.repository.ChallengeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChallengeRepository(
        impl: ChallengeRepositoryImpl
    ): ChallengeRepository
}


interface ChallengeRepository {
    fun getAllLocalChallenges(): Flow<List<ChallengeEntity>>
    fun fetchChallengesFromFirebase(): Flow<List<ChallengeEntity>>
    suspend fun getLocalChallengeById(id: String): ChallengeEntity?
    suspend fun addChallengeLocal(challenge: ChallengeEntity)
    suspend fun updateChallengeLocal(challenge: ChallengeEntity)
    suspend fun deleteChallengeLocal(challenge: ChallengeEntity)
    suspend fun uploadChallengeToFirebase(challenge: ChallengeEntity)
    suspend fun uploadImageToFirebase(imageUri: Uri?): String
    suspend fun updateChallengeFirebase(challenge: ChallengeEntity)
    suspend fun deleteChallengeFromFirebase(firebaseId: String)
}

@Singleton
class ChallengeRepositoryImpl @Inject constructor(
    private val challengeDao: ChallengeDao
) : ChallengeRepository {

    private val firebaseDb = FirebaseDatabase.getInstance().reference
    private val firebaseStorage = FirebaseStorage.getInstance().reference

    override fun getAllLocalChallenges(): Flow<List<ChallengeEntity>> = challengeDao.getAll()

    override fun fetchChallengesFromFirebase(): Flow<List<ChallengeEntity>> = callbackFlow {
        val dbRef = firebaseDb.child("eco_challenges")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val challenges = mutableListOf<ChallengeEntity>()
                for (child in snapshot.children) {
                    val challenge = child.getValue(ChallengeEntity::class.java)
                    challenge?.let { challenges.add(it) }
                }
                trySend(challenges).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        dbRef.addValueEventListener(listener)
        awaitClose { dbRef.removeEventListener(listener) }
    }

    override suspend fun getLocalChallengeById(id: String): ChallengeEntity? =
        challengeDao.getChallengeById(id)

    override suspend fun addChallengeLocal(challenge: ChallengeEntity) {
        challengeDao.insertChallenge(challenge)
    }

    override suspend fun updateChallengeLocal(challenge: ChallengeEntity) {
        challengeDao.updateChallenge(challenge)
    }

    override suspend fun deleteChallengeLocal(challenge: ChallengeEntity) {
        challengeDao.deleteChallenge(challenge)
    }

    override suspend fun uploadImageToFirebase(imageUri: Uri?): String {
        if (imageUri == null) return ""
        val imageRef = firebaseStorage.child("challenge_images/${UUID.randomUUID()}.jpg")
        try {
            imageRef.putFile(imageUri).await()
            return imageRef.downloadUrl.await().toString()
        } catch (e: Exception) {
            throw Exception("Failed to upload image: ${e.message}", e)
        }
    }

    override suspend fun uploadChallengeToFirebase(challenge: ChallengeEntity) {
        val newRef = firebaseDb.child("eco_challenges").push()
        val firebaseId = newRef.key ?: return
        val challengeWithId = challenge.copy(id = firebaseId)
        newRef.setValue(challengeWithId).addOnSuccessListener {
            Log.d("Firebase", "Challenge uploaded with ID $firebaseId")
        }.addOnFailureListener {
            Log.e("Firebase", "Upload failed", it)
        }
    }

    override suspend fun updateChallengeFirebase(challenge: ChallengeEntity) {
        if (challenge.id.isBlank()) throw IllegalArgumentException("Firebase ID is empty")
        firebaseDb.child("eco_challenges").child(challenge.id).setValue(challenge).await()
    }

    override suspend fun deleteChallengeFromFirebase(firebaseId: String) {
        if (firebaseId.isBlank()) throw IllegalArgumentException("Firebase ID is empty")
        firebaseDb.child("eco_challenges").child(firebaseId).removeValue().await()
    }
}

