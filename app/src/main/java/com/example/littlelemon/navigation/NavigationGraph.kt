package com.example.littlelemon

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.littlelemon.data.AppDatabase
import com.example.littlelemon.data.UserDataStore
import com.example.littlelemon.repository.MenuRepository

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation(repository: MenuRepository) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Onboarding.route) {

        composable(Screen.Onboarding.route) {
            OnboardingScreen(repository) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Onboarding.route) { inclusive = true } // remove onboarding from back stack
                }
            }
        }

        composable(Screen.Home.route) {
            HomeScreen(db = AppDatabase.getDatabase(UserDataStore.context)) {
                navController.navigate(Screen.Profile.route)
            }
        }

        composable(Screen.Profile.route) {
            ProfileScreen {
                // logout -> go back to onboarding
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            }
        }
    }
}
