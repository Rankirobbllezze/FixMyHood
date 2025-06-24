package com.alenga.fixmyhood.presentation.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alenga.fixmyhood.data.local.ChallengeEntity
import com.alenga.fixmyhood.data.repository.ChallengeRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: ChallengeRepository
) : ViewModel() {

    private val _dailyTip = MutableStateFlow("Turn off lights when not in use.")
    val dailyTip: StateFlow<String> = _dailyTip


    init {
        fetchDailyTip()
    }

    private fun fetchDailyTip() {
        val tips = listOf(
            "Use reusable bags instead of plastic.",
            "Bike or walk short distances.",
            "Turn off lights when not in use.",
            "Avoid fast fashion—reuse clothes!"
        )
        _dailyTip.value = tips.random()
    }

    // ✅ List of local challenges (Room)
    val localChallenges: StateFlow<List<ChallengeEntity>> =
        repository.getAllLocalChallenges()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // ✅ List from Firebase
    val firebaseChallenges: StateFlow<List<ChallengeEntity>> =
        repository.fetchChallengesFromFirebase()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // ✅ Add challenge locally and to Firebase
    fun addEcoChallenge(
        title: String,
        description: String,
        category: String,
        tasker: String,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            try {
                val imageUrl = repository.uploadImageToFirebase(imageUri)
                val challenge = ChallengeEntity(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    description = description,
                    category = category,
                    tasker = tasker,
                    imageUri = imageUrl,
                    isCompleted = false
                )
                repository.addChallengeLocal(challenge)
                repository.uploadChallengeToFirebase(challenge)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Update challenge in Firebase
    fun updateChallenge(challenge: ChallengeEntity) {
        viewModelScope.launch {
            repository.updateChallengeFirebase(challenge)
            repository.updateChallengeLocal(challenge)
        }
    }

    // ✅ Delete challenge from Firebase
    fun deleteChallengeFromFirebase(challenge: ChallengeEntity) {
        viewModelScope.launch {
//            repository.deleteChallengeFirebase(challenge)
            repository.deleteChallengeLocal(challenge)
        }
    }

    // ✅ Toggle challenge completion
    fun toggleChallengeCompletion(challengeId: String) {
        viewModelScope.launch {
            val challenge = localChallenges.value.find { it.id == challengeId } ?: return@launch
            val updated = challenge.copy(isCompleted = !challenge.isCompleted)
            updateChallenge(updated)
        }
    }

    // ✅ Optional: Password reset
    fun sendPasswordReset(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onError(task.exception?.message ?: "An error occurred")
                }
            }
    }

}
