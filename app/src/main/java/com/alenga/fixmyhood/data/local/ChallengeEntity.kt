package com.alenga.fixmyhood.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val isCompleted: Boolean
)
