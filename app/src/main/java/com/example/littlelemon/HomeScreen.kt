package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemon.data.MenuItemEntity
import com.example.littlelemon.viewmodel.MenuViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(menuViewModel: MenuViewModel? = null) {

    val DarkGreen = Color(0xFF495E57)
    val Gray = Color(0xFF9E9E9E)

    val fakeMenuItems = listOf(
        MenuItemEntity(1, "Greek Salad", "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.", 12.0, R.drawable.greek_salad, "Starters"),
        MenuItemEntity(2, "Lemon Chicken", "Juicy chicken", 18.0, R.drawable.lemonchicken, "Mains"),
        MenuItemEntity(3, "Baklava", "A rich, layered pastry made of ultra-thin phyllo dough, filled with chopped nuts and soaked in a sweet syrup or honey after baking", 6.0, R.drawable.baklavaa, "Desserts"),
        MenuItemEntity(4, "Bruschetta", "Our Bruschetta is made from grilled bread that has been smeared with garlic and seasoned with salt and olive oil.", 6.0, R.drawable.bruschetta, "Mains")

    )

    val menuItems: State<List<MenuItemEntity>> =
        menuViewModel?.menuItems?.collectAsState(initial = fakeMenuItems) ?: remember { mutableStateOf(fakeMenuItems) }

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var selectedCategory by remember { mutableStateOf("All") }
    val categories = listOf("Starters", "Mains", "Desserts", "Drinks")

    val filteredItems = menuItems.value.filter { item ->
        (selectedCategory == "All" || item.category.equals(selectedCategory, true)) &&
                item.title.contains(searchQuery.text, ignoreCase = true)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(50.dp)
                        .width(200.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hero Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(DarkGreen)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 12.dp)
                    ) {
                        Text("Little Lemon", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                        Text("Chicago", style = MaterialTheme.typography.bodyMedium, color = Color.White)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White
                        )
                    }

                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Search menu", color = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        cursorColor = Color.White,
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Category Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                categories.forEach { category ->
                    Button(
                        onClick = { selectedCategory = category },
                        colors = ButtonDefaults.buttonColors(containerColor = Gray),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(text = category, color = DarkGreen, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        items(filteredItems) { item ->
            MenuItemCard(item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}
