package com.alenga.fixmyhood.presentation.screens.addeco


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.alenga.fixmyhood.data.local.ChallengeEntity
import com.alenga.fixmyhood.data.model.Challenge
import com.alenga.fixmyhood.data.model.Challenge.EcoChallenge

@Composable
fun EditEcoForm(
    challenge: ChallengeEntity,
//    navController: NavHostController,
    onSubmit: (ChallengeEntity) -> Unit,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    var title by remember { mutableStateOf(challenge.title) }
    var description by remember { mutableStateOf(challenge.description) }
    var category by remember { mutableStateOf(challenge.category ?: "") }
    var tasker by remember { mutableStateOf(challenge.tasker ?: "") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Edit Eco Challenge",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = title, onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = description, onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = category, onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        OutlinedTextField(
            value = tasker, onValueChange = { tasker = it },
            label = { Text("Tasker Name") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Change Image")
        }

        imageUri?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(180.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                // Save logic goes here
               onDismiss
            }) {
                Text("Save")
            }
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val updated = challenge.copy(
                            title = title,
                            description = description,
                            category = category,
                            tasker = tasker,
                            imageUri = imageUri?.toString() ?: challenge.imageUri
                        )
                        onSubmit(updated)
                        onDismiss()
                    }
                },
                enabled = title.isNotBlank()
            ) {
                Text("Save Changes")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditEcoFormPreview() {
    val challenge = ChallengeEntity(
        id = "1",
        title = "Clean Beach",
        description = "Organize a community beach cleanup",
        category = "Clean-up",
        tasker = "Local Youth Group",
        imageUri = null,
        isCompleted = false
    )

    EditEcoForm(challenge = challenge, onSubmit = {}, onDismiss = {})
}
