package com.alenga.fixmyhood.data.model

class Challenge {
    data class EcoChallenge(
        val id: Int,
        val title: String,
        var isCompleted: Boolean = false,
        val category: String,
        val description: String,
        val tasker: String,
        val imageUri: String? = null,

    )

}