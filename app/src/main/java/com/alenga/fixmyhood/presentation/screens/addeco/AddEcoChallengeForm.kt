package com.alenga.fixmyhood.presentation.screens.addeco

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.alenga.fixmyhood.R
import com.alenga.fixmyhood.presentation.viewmodel.AddEcoChallengeViewModel
import com.alenga.fixmyhood.presentation.viewmodel.DashboardViewModel

@Composable
fun AddEcoChallengeForm(
    navController: NavController,

    onDismiss: () -> Unit,

) {
    val viewModel: AddEcoChallengeViewModel = hiltViewModel()
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> imageUri = uri }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var tasker by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Add Environmental Challenge", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )
        OutlinedTextField(
            value = tasker,
            onValueChange = { tasker = it },
            label = { Text("Tasker") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        )

        Button(onClick = { imagePickerLauncher.launch("image/*") }) {
            Text("Select Image")
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
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
            Button(onClick = {
                viewModel.addEcoChallengeToFirebase(
                    title = title,
                    description = description,
                    category = category,
                    tasker = tasker,
                    imageUri = imageUri?.toString(),
                    onSuccess = {
                        navController.navigate("eco_card")
                    },
                    onFailure = { errorMsg ->
                        Log.e("SubmitError", errorMsg)
                    }
                )
            }) {
                Text("Submit")
            }



        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddEcoChallengeFormPreview() {
    AddEcoChallengeForm(
        navController = rememberNavController(),

        onDismiss = {
            println("Form dismissed")
        }
    )
}

