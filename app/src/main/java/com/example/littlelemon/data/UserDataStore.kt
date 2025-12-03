package com.example.littlelemon.data

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class UserDataStore(private val context: Context) {

    companion object {
        val NAME_KEY = stringPreferencesKey("name")
        val EMAIL_KEY = stringPreferencesKey("email")
    }

    val userFlow: Flow<Pair<String, String>> = context.dataStore.data
        .map { prefs: Preferences ->
            val name = prefs[NAME_KEY] ?: ""
            val email = prefs[EMAIL_KEY] ?: ""
            Pair(name, email)
        }

    suspend fun saveUser(name: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { it.clear() }
    }
}
