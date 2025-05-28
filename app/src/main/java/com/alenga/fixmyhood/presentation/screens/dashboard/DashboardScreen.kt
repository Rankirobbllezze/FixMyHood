package com.alenga.fixmyhood.presentation.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel = viewModel()
) {
    val ecoTip by viewModel.dailyTip.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Welcome back!", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        Text("🌿 Today's Eco Tip", style = MaterialTheme.typography.titleMedium)
        Text(text = ecoTip, style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate("challenges") // make sure this route exists in your NavHost
        }) {
            Text("Start a Challenge")
        }
    }
}


