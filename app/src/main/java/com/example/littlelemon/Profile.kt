package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController

// Reuse the custom colors


@Composable
fun ProfileScreen(navController: NavHostController, context: Context) {
    // Retrieve user info from SharedPreferences
    val sharedPref = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val firstName = sharedPref.getString("firstName", "") ?: ""
    val lastName = sharedPref.getString("lastName", "") ?: ""
    val email = sharedPref.getString("email", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo Header
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(80.dp)
                .width(200.dp)
                .padding(bottom = 16.dp)
        )



        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Personal information",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = DarkGray,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Display user info using Boxes that look like TextFields
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,                     // thickness of the border
                    color = DarkGray,                 // dark gray color
                    shape = RoundedCornerShape(8.dp)  // rounded corners
                )
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "First Name: $firstName", color = DarkGray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,                     // thickness of the border
                    color = DarkGray,                 // dark gray color
                    shape = RoundedCornerShape(8.dp)  // rounded corners
                )
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Last Name: $lastName", color = DarkGray)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,                     // thickness of the border
                    color = DarkGray,                 // dark gray color
                    shape = RoundedCornerShape(8.dp)  // rounded corners
                )
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Email: $email", color = DarkGray)
        }

        Spacer(modifier = Modifier.height(62.dp))

        // Logout Button
        Button(
            onClick = {
                sharedPref.edit().clear().apply()
                navController.navigate(Onboarding.route) {
                    popUpTo("Profile") { inclusive = true }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Log out",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    ProfileScreen(navController = navController, context = context)
}
