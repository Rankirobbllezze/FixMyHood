package com.alenga.fixmyhood.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEcoChallengeViewModel @Inject constructor() : ViewModel() {

    fun addEcoChallengeToFirebase(
        title: String,
        description: String,
        category: String,
        tasker: String,
        imageUri: String?,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val db = FirebaseFirestore.getInstance()

        val newChallenge = hashMapOf(
            "title" to title,
            "description" to description,
            "category" to category,
            "tasker" to tasker,
            "imageUri" to imageUri,
            "isCompleted" to false
        )

        db.collection("eco_challenges")
            .add(newChallenge)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to add challenge")

            }
    }
}

