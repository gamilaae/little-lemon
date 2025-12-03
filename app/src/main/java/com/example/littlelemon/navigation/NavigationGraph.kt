package com.example.littlelemon.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.littlelemon.HomeScreen
import com.example.littlelemon.OnboardingScreen
import com.example.littlelemon.ProfileScreen
import com.example.littlelemon.data.DataStoreManager
import com.example.littlelemon.viewmodel.MenuViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Composable
fun NavigationGraph(
    navController: NavHostController,
    menuViewModel: MenuViewModel,
    dataStoreManager: DataStoreManager,
    modifier: Modifier = Modifier,
    scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
) {
    NavHost(
        navController = navController,
        startDestination = Destination.ONBOARDING,
        modifier = modifier
    ) {
        composable(Destination.ONBOARDING) {
            OnboardingScreen(navController, dataStoreManager, scope)
        }
        composable(Destination.HOME) {
            HomeScreen(menuViewModel)
        }
        composable(Destination.PROFILE) {
            ProfileScreen(
                dataStoreManager = dataStoreManager,
                scope = scope
            )        }
    }
}
