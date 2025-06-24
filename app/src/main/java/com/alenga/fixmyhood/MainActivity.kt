package com.alenga.fixmyhood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alenga.fixmyhood.presentation.components.EcoCard
import com.alenga.fixmyhood.presentation.components.EcoChallenge
import com.alenga.fixmyhood.presentation.components.LoginScreen
import com.alenga.fixmyhood.presentation.components.SignUpScreen
import com.alenga.fixmyhood.presentation.screens.ChallengeScreen
import com.alenga.fixmyhood.presentation.screens.DashboardScreen
import com.alenga.fixmyhood.presentation.screens.EducationScreen
import com.alenga.fixmyhood.presentation.screens.ProfileScreen
import com.alenga.fixmyhood.presentation.screens.addeco.AddEcoChallengeForm
import com.alenga.fixmyhood.presentation.screens.addeco.EditEcoForm
import com.alenga.fixmyhood.presentation.viewmodel.AddEcoChallengeViewModel
import com.alenga.fixmyhood.presentation.viewmodel.DashboardViewModel
import com.alenga.fixmyhood.ui.theme.FixMyHoodTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            FixMyHoodTheme {
                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }

//            // inside setContent { ... }
//            val navController = rememberNavController()
//
//// Determine start destination dynamically
//            val startDestination = if (FirebaseAuth.getInstance().currentUser != null) {
//                "dashboard"
//            } else {
//                "login"
//            }
        }
    }
    override fun onStart() {
        super.onStart()
        // CUSTOM ONSTART LOGIC FOR THIS ACTIVITY
    }
}


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController = navController)
        }

        composable("signup") {
            SignUpScreen(navController = navController)
        }

        composable("dashboard") {
            DashboardScreen(navController = navController)
        }

        composable("challenges") {
            ChallengeScreen(navController = navController)
        }

        composable("add_challenge") {
            val viewModel: AddEcoChallengeViewModel = hiltViewModel()
            AddEcoChallengeForm(
                navController = navController,

                onDismiss = {
                    navController.popBackStack()
                }
            )
        }

        composable("eco_card") {
            EcoCard(
                challenge = EcoChallenge(
                    id = "1",
                    title = "Plant a Tree",
                    description = "Join a tree planting event to support reforestation.",
                    category = "Conservation",
                    isCompleted = false
                ),
                onCompleteChange = {},
                onEditClick = {},
                onDeleteClick = {}
            )
        }
        composable("edit_challenge") {
            EcoCard(
            challenge = EcoChallenge(
                id = "1",
                title = "Edit Me",
                description = "Edit Description",
                category = "Edit Category",
                isCompleted = false
            ),
                onCompleteChange = {},
                onEditClick = {},
                onDeleteClick = {}

            )
        }


        composable("education") {
            EducationScreen(navController = navController)
        }

        composable("profile") {
            ProfileScreen(onLogout = {
                navController.navigate("login") {
                    popUpTo("dashboard") { inclusive = true }
                }
            })
        }
    }
}
