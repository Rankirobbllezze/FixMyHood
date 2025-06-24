package com.alenga.fixmyhood.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.alenga.fixmyhood.data.local.ChallengeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChallengeDao {
    @Query("SELECT * FROM challenges")
    fun getAll(): Flow<List<ChallengeEntity>>

    @Query("SELECT * FROM challenges WHERE id = :id LIMIT 1")
    suspend fun getChallengeById(id: String): ChallengeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChallenge(challenge: ChallengeEntity)

    @Update
    suspend fun updateChallenge(challenge: ChallengeEntity)

    @Delete
    suspend fun deleteChallenge(challenge: ChallengeEntity)
}

