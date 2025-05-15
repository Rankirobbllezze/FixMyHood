package com.alenga.fixmyhood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.alenga.fixmyhood.ui.theme.FixMyHoodTheme
import com.example.robbllezze.presentation.navigation.TodoNavGraph
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // initialize firebase for the app
        FirebaseApp.initializeApp(this)
        setContent {
            FixMyHoodTheme  {
                val navController = rememberNavController()
                TodoNavGraph(navController)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // CUSTOM ONSTART LOGIC FOR THIS ACTIVITY
    }



}