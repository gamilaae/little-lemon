package com.example.littlelemon

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.littlelemon.application.App
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("user_prefs")

object UserDataStore {

    private val NAME_KEY = stringPreferencesKey("name")
    private val EMAIL_KEY = stringPreferencesKey("email")

    suspend fun saveUser(name: String, email: String) {
        App.context.dataStore.edit { prefs ->
            prefs[NAME_KEY] = name
            prefs[EMAIL_KEY] = email
        }
    }

    suspend fun clearUser() {
        App.context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }

    suspend fun getUser(): Pair<String, String> {
        val prefs = App.context.dataStore.data.first()
        val name = prefs[NAME_KEY] ?: ""
        val email = prefs[EMAIL_KEY] ?: ""
        return Pair(name, email)
    }
}
