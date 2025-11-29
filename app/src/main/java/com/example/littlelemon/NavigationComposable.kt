package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNavigation(startDestination: String) {

    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController, context = context)
        }
        composable(Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Profile.route) {
            ProfileScreen(navController = navController, context = context)
        }
    }
}
