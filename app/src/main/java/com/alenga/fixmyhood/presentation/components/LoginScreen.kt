package com.alenga.fixmyhood.presentation.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }



    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {

//                space
            Spacer(modifier = Modifier.height(32.dp))

            Text("Login", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(value = email, onValueChange = { email = it },
                label = { Text("Email") })

            OutlinedTextField(value = password, onValueChange = { password = it},
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation())

            error?.let { Text(it, color = MaterialTheme.colorScheme.error) }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (email.isBlank() || password.isBlank()) {
                    error = "Email and password are required"
                } else {
                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("dashboard") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }) {
                Text("Login")
            }
            TextButton(onClick = {
                navController.navigate("signUp")
            }) {
                Text("Don't have an account? Sign up")
            }
            TextButton(onClick = { navController.navigate("forgot_password") }) {
                Text("Forgot Password?")
            }

        }
    }



}


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(rememberNavController())
}