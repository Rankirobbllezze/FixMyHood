package com.alenga.fixmyhood.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DashboardViewModel : ViewModel() {
    private val _dailyTip = MutableStateFlow("Turn off lights when not in use.")
    val dailyTip: StateFlow<String> = _dailyTip

    init {
        fetchDailyTip()
    }

    private fun fetchDailyTip() {
        // In a real app, fetch from database or remote API
        val tips = listOf(
            "Use reusable bags instead of plastic.",
            "Bike or walk short distances.",
            "Turn off lights when not in use.",
            "Avoid fast fashion—reuse clothes!"
        )
        _dailyTip.value = tips.random()
    }
}

