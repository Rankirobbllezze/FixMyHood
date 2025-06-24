package com.alenga.fixmyhood.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.alenga.fixmyhood.presentation.viewmodel.EducationViewModel
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alenga.fixmyhood.presentation.viewmodel.ChallengeViewModel

@Composable
fun EducationScreen(viewModel: EducationViewModel = hiltViewModel(),
                    navController: NavHostController) {
    val education by viewModel.challenges.collectAsState()
    val resources = listOf(
        "🌿 How to Start Composting",
        "♻️ Recycling Guide for Beginners",
        "🚰 Save Water: 10 Easy Tips",
        "🌍 The Impact of Fast Fashion",
        "📺 Watch: The Story of Plastic"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("📚 Education Hub", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(resources) { resource ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
                ) {
                    Text(
                        text = resource,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                education.forEach { education ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            viewModel.toggleChallengeCompletion(education)
                        }
                ) {
                    Checkbox(
                        checked = education.isCompleted,
                        onCheckedChange = {
                            viewModel.toggleChallengeCompletion(education)
                        }
                    )
                    Text(text = education.title, modifier = Modifier.padding(start = 8.dp))
                }
            }


            }
        }
    }
}
