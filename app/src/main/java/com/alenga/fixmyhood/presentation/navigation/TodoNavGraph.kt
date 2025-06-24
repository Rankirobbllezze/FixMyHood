package com.alenga.fixmyhood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alenga.fixmyhood.presentation.components.EcoCard
import com.alenga.fixmyhood.presentation.components.EcoChallenge
import com.alenga.fixmyhood.presentation.components.ForgotPassword
import com.alenga.fixmyhood.presentation.components.LoginScreen
import com.alenga.fixmyhood.presentation.components.SignUpScreen
import com.alenga.fixmyhood.presentation.screens.ChallengeScreen
import com.alenga.fixmyhood.presentation.screens.DashboardScreen
import com.alenga.fixmyhood.presentation.screens.EducationScreen
import com.alenga.fixmyhood.presentation.screens.ProfileScreen
import com.alenga.fixmyhood.presentation.screens.addeco.AddEcoChallengeForm
import com.alenga.fixmyhood.presentation.viewmodel.AddEcoChallengeViewModel

@Composable
fun TOdoNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("signUp") {
            SignUpScreen(navController = navController)
        }
        composable("login") {
            LoginScreen(navController = navController)
        }
        composable("forgot_password") {
            ForgotPassword(navController = navController)
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
        composable("education") {
            EducationScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
    }
}
