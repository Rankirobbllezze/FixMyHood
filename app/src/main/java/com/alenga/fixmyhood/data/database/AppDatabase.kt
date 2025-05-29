package com.alenga.fixmyhood.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.alenga.fixmyhood.data.dao.ChallengeDao
import com.alenga.fixmyhood.data.local.ChallengeEntity

@Database(entities = [ChallengeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun challengeDao(): ChallengeDao
}

