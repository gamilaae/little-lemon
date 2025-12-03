package com.example.littlelemon.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("user_prefs")

class UserPreferences(context: Context) {
    private val dataStore = context.dataStore

    val userName: Flow<String?> = dataStore.data.map { it[PreferencesKeys.NAME] }
    val userEmail: Flow<String?> = dataStore.data.map { it[PreferencesKeys.EMAIL] }

    suspend fun saveUser(name: String, email: String) {
        dataStore.edit { prefs ->
            prefs[PreferencesKeys.NAME] = name
            prefs[PreferencesKeys.EMAIL] = email
        }
    }

    suspend fun clearUser() {
        dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    private object PreferencesKeys {
        val NAME = stringPreferencesKey("user_name")
        val EMAIL = stringPreferencesKey("user_email")
    }
}
