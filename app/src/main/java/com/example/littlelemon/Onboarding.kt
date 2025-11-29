package com.example.littlelemon

import android.R.attr.fontWeight
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalContext

// Custom Colors
val Yellow = Color(0xFFF4CE14)
val DarkGray = Color(0xFF333333)
val LightGray = Color(0xFFE0E0E0)
val DarkGreen = Color(0xFF495E57)

@Composable
fun OnboardingScreen(navController: NavHostController, context: Context) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(100.dp)
                .width(200.dp)
                .padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkGreen, RoundedCornerShape(8.dp))
                .padding(vertical = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = LightGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Personal information",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = DarkGray
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(110.dp))

        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    message = "Registration unsuccessful. Please enter all data."
                } else {
                    saveUserData(context, firstName, lastName, email)
                    message = "Registration successful!"

                    navController.navigate(Home.route) {
                        popUpTo(Onboarding.route) { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)

         
        ) {
            Text(
                text = "Register",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotBlank()) {
            Text(message, color = Color.Red)
        }
    }
}

private fun ColumnScope.Button(
    onClick: () -> Unit,
    colors: ButtonColors,
    modifier: Modifier,
    shape: Modifier,
    content: (RowScope) -> Unit
) {
}

fun saveUserData(context: Context, first: String, last: String, email: String) {
    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    sharedPrefs.edit().apply {
        putString("firstName", first)
        putString("lastName", last)
        putString("email", email)
        apply()
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    val navController = rememberNavController()
    val context = LocalContext.current
    OnboardingScreen(navController = navController, context = context)
}
