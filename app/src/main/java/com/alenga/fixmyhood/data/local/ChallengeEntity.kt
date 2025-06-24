package com.alenga.fixmyhood.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenges")
data class ChallengeEntity(
    @PrimaryKey val id: String,
    val firebase_id: String = "",
    val title: String = "",
    val isCompleted: Boolean = false,
    val category: String = "",
    val description: String = "",
    val tasker: String = "",
    val createdAt: Long = System.currentTimeMillis(), // capture time
    val imageUri: String? = null,
)
