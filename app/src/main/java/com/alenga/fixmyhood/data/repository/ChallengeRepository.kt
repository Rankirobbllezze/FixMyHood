package com.alenga.fixmyhood.data.repository


import com.alenga.fixmyhood.data.dao.ChallengeDao
import com.alenga.fixmyhood.data.local.ChallengeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChallengeRepository @Inject constructor(
    private val challengeDao: ChallengeDao
) {

    fun getAllChallenges(): Flow<List<ChallengeEntity>> = challengeDao.getAll()


    suspend fun insertChallenges(challenges: List<ChallengeEntity>) {
        challengeDao.insertAll(challenges)
    }

    suspend fun updateChallenge(challenge: ChallengeEntity) {
        challengeDao.updateChallenge(challenge)
    }
}
