package com.example.littlelemon

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.datastore.DataStoreManager
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    navController: NavHostController,
    dataStore: DataStoreManager
) {
    val scope = rememberCoroutineScope()

    // Load values from DataStore
    val firstName by dataStore.userFirstName.collectAsState(initial = "")
    val lastName by dataStore.userLastName.collectAsState(initial = "")
    val email by dataStore.userEmail.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium
        )

        // First name
        OutlinedTextField(
            value = firstName,
            onValueChange = {},
            label = { Text("First Name") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        // Last name
        OutlinedTextField(
            value = lastName,
            onValueChange = {},
            label = { Text("Last Name") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Log out button
        Button(
            onClick = {
                scope.launch {
                    dataStore.clearUser()
                    navController.navigate(Onboarding.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}
