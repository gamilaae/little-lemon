package com.example.littlelemon.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.InternalSerializationApi

@OptIn(InternalSerializationApi::class)
val httpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
}

@OptIn(InternalSerializationApi::class)
suspend fun fetchMenu(): List<MenuItemNetwork> {
    val response: MenuNetwork = httpClient.get(
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    ).body()
    return response.menu
}