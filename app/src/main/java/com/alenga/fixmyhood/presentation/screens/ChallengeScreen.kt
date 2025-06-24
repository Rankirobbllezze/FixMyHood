package com.alenga.fixmyhood.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alenga.fixmyhood.presentation.viewmodel.ChallengeViewModel

@Composable
fun ChallengeScreen(
    viewModel: ChallengeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val challenges by viewModel.challenges.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Challenges", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(8.dp))

        challenges.forEach { challenge ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        viewModel.toggleChallengeCompletion(challenge)
                    }
            ) {
                Checkbox(
                    checked = challenge.isCompleted,
                    onCheckedChange = {
                        viewModel.toggleChallengeCompletion(challenge)
                    }
                )
                Text(text = challenge.title, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("addChallenge")
            }
        ) {
            Text("Start a Challenge")
        }
    }
}
