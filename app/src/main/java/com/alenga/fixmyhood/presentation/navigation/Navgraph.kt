package com.alenga.fixmyhood.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alenga.fixmyhood.presentation.components.LoginScreen
import com.alenga.fixmyhood.presentation.components.SignUpScreen
import com.alenga.fixmyhood.presentation.screens.ChallengeScreen
import com.alenga.fixmyhood.presentation.screens.DashboardScreen
import com.alenga.fixmyhood.presentation.screens.EducationScreen
import com.alenga.fixmyhood.presentation.screens.ProfileScreen

//import com.example.robbllezze.presentation.screens.dashboard.DashboardScreen

// INSIDE THIS FILE WE WILL DEFINE NAVCONTROLLER : THIS IS USED TO NAVIGATE
// TO DIFFERENT COMPOSABLES / SCREENS
@Composable
fun TodoNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = "signUp") {
        composable("signUp"){
            SignUpScreen(navController)
        }
        composable("login"){
            LoginScreen(navController)
        }
        composable("dashboard"){
            DashboardScreen(
                //properties for the composable
                navController
            )

        }
        composable("challenges") {
            ChallengeScreen(navController = navController)
        }
        composable("education") {
            EducationScreen()
        }
        composable("profile") {
            ProfileScreen(onLogout = {
                navController.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            })
        }




        // here will define the addtoDo composable

    }

}