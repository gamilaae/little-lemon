@file:OptIn(kotlinx.serialization.InternalSerializationApi::class) // Apply opt-in to entire file

package com.example.littlelemon.network

import com.example.littlelemon.data.MenuItemEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import com.example.littlelemon.R


// ----------------------------
// Network Data Classes
// ----------------------------
@Serializable
data class MenuItemNetwork(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String = ""
)

@Serializable
data class MenuNetwork(
    val menu: List<MenuItemNetwork>
)

// ----------------------------
// Ktor HTTP Client
// ----------------------------
val httpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json()
    }
}

// ----------------------------
// Fetch menu from network
// ----------------------------
suspend fun fetchMenuFromNetwork(category: String? = null): List<MenuItemNetwork> {
    // Explicitly deserialize to MenuNetwork
    val menuNetwork: MenuNetwork = httpClient.get(
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    ).body()

    return if (category.isNullOrEmpty()) {
        menuNetwork.menu
    } else {
        menuNetwork.menu.filter { it.category.equals(category, ignoreCase = true) }
    }
}

// ----------------------------
// Convert network list to Room entities
// ----------------------------
fun List<MenuItemNetwork>.toEntityList(): List<MenuItemEntity> = this.map {
    MenuItemEntity(
        id = 1,
        title = "Greek Salad",
        description = "Fresh veggies",
        price = 12.0,
        imageRes = R.drawable.greek_salad,  // ✅ use local drawable resource
        category = "Starters"
    )

    MenuItemEntity(
        id = 2,
        title = "Lemon Chicken",
        description = "Juicy chicken",
        price = 18.0,
        imageRes = R.drawable.lemonchicken, // ✅ local drawable
        category = "Mains"
    )

    MenuItemEntity(
        id = 3,
        title = "Baklava",
        description = "Sweet dessert",
        price = 6.0,
        imageRes = R.drawable.baklavaa, // ✅ local drawable
        category = "Desserts"
    )

}
