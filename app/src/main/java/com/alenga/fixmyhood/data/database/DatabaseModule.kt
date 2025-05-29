package com.alenga.fixmyhood.data.database

import com.alenga.fixmyhood.data.dao.ChallengeDao
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "eco_challenges_db"
        ).build()
    }

    @Provides
    fun provideChallengeDao(db: AppDatabase): ChallengeDao {
        return db.challengeDao()
    }
}
