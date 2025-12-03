package com.example.littlelemon.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.DataStoreManager
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.viewmodel.MenuViewModel
import com.example.littlelemon.application.LittleLemonApplication

import com.example.littlelemon.navigation.NavigationGraph


class App : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val menuViewModel = MenuViewModel(repository = (application as LittleLemonApplication).repository)
        val dataStoreManager = DataStoreManager(this)

        setContent {
            LittleLemonTheme {
                val navController = rememberNavController()
                NavigationGraph(navController, menuViewModel, dataStoreManager)
            }
        }
    }
}
