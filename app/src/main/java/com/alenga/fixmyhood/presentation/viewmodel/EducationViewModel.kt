package com.alenga.fixmyhood.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alenga.fixmyhood.data.local.ChallengeEntity
import com.alenga.fixmyhood.data.repository.ChallengeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class EducationViewModel @Inject constructor(
    private val repository: ChallengeRepository
) : ViewModel() {

    private val _challenges = MutableStateFlow<List<ChallengeEntity>>(emptyList())
    val challenges: StateFlow<List<ChallengeEntity>> = _challenges

    init {
        loadChallenges()
    }

    private fun loadChallenges() {
        viewModelScope.launch {
            repository.getAllLocalChallenges().collect { list ->
                _challenges.value = list
            }
        }
    }

    fun toggleChallengeCompletion(challenge: ChallengeEntity) {
        viewModelScope.launch {
            val updated = challenge.copy(isCompleted = !challenge.isCompleted)
            repository.updateChallengeLocal(updated)
            loadChallenges()
        }
    }
}
