package com.example.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.application.LittleLemonApplication
import com.example.littlelemon.data.DataStoreManager
import com.example.littlelemon.navigation.NavigationGraph
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.example.littlelemon.viewmodel.MenuViewModel
import com.example.littlelemon.viewmodel.MenuViewModelFactory

class MainActivity : ComponentActivity() {

    private val menuViewModel: MenuViewModel by viewModels {
        MenuViewModelFactory((application as LittleLemonApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            Log.d("DEBUG", "MainActivity started")

            val dataStoreManager = DataStoreManager(this)
            Log.d("DEBUG", "DataStoreManager initialized")

            setContent {
                LittleLemonTheme {
                    val navController = rememberNavController()
                    NavigationGraph(
                        navController = navController,
                        menuViewModel = menuViewModel,
                        dataStoreManager = dataStoreManager
                    )
                }
            }

            Log.d("DEBUG", "setContent completed")
        } catch (e: Exception) {
            Log.e("DEBUG", "Error in MainActivity: ${e.message}", e)
        }
    }
}
