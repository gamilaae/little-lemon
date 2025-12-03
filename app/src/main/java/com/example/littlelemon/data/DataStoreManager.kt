package com.example.littlelemon.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    private val nameKey = stringPreferencesKey("name")
    private val emailKey = stringPreferencesKey("email")

    // Save user data
    suspend fun saveUser(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[nameKey] = name
            prefs[emailKey] = email
        }
    }

    // Get user data
    suspend fun getUser(): Pair<String, String> {
        val prefs = context.dataStore.data.first()
        val name = prefs[nameKey] ?: ""
        val email = prefs[emailKey] ?: ""
        return Pair(name, email)
    }

    // Clear user data
    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
