package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startDestination =
            if (userIsLoggedIn()) Home.route
            else Onboarding.route

        setContent {
            MyNavigation(startDestination = startDestination)
        }
    }

    private fun userIsLoggedIn(): Boolean {
        val sharedPrefs = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val firstName = sharedPrefs.getString("firstName", "")
        val lastName = sharedPrefs.getString("lastName", "")
        val email = sharedPrefs.getString("email", "")

        // If ANY field is missing, user is NOT logged in
        return !(firstName.isNullOrBlank() ||
                lastName.isNullOrBlank() ||
                email.isNullOrBlank())
    }
}
