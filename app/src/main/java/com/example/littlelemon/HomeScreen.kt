package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.data.MenuItemEntity
import com.example.littlelemon.ui.MenuItemCard

@Composable
fun HomeScreen(navController: NavHostController, menuViewModel: MenuViewModel) {

    val menuItems: List<MenuItemEntity> = menuViewModel.menuItems.value

    Column(modifier = Modifier.fillMaxSize()) {

        // Profile Icon
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clickable {
                        navController.navigate(Profile.route)
                    }
            )
        }

        // Menu List
        MenuItemsList(menuItems)
    }
}

@Composable
fun MenuItemsList(items: List<MenuItemEntity>) {
    Column {
        for (item in items) {
            MenuItemCard(menuItem = item)
        }
    }
}
