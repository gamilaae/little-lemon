package com.example.littlelemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.DataStoreManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val dataStoreManager: DataStoreManager) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    init {
        viewModelScope.launch {
            val user = dataStoreManager.getUser()
            _name.value = user.first
            _email.value = user.second
        }
    }

    fun logout(onComplete: () -> Unit) {
        viewModelScope.launch {
            dataStoreManager.clearUser()
            onComplete()
        }
    }
}
