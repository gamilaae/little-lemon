package com.example.littlelemon.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.MenuItemEntity
import com.example.littlelemon.repository.MenuRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MenuViewModel(private val repository: MenuRepository?) : ViewModel() {

    // Private state for menu items
    private val _menuItems = MutableStateFlow<List<MenuItemEntity>>(emptyList())
    val menuItems: StateFlow<List<MenuItemEntity>> = _menuItems

    init {
        // Fetch from repository if available
        if (repository != null) {
            viewModelScope.launch {
                repository.menuItems.collect { items ->
                    _menuItems.value = items
                }
            }
        }
    }

    // -----------------------------
    // Public setter for preview/fake data
    // -----------------------------
    fun setMenuItemsForPreview(items: List<MenuItemEntity>) {
        _menuItems.value = items
    }

    // -----------------------------
    // Refresh menu from network
    // -----------------------------
    fun refreshMenuFromNetwork(category: String? = null) {
        repository?.let { repo ->
            viewModelScope.launch {
                repo.refreshMenuFromNetwork()
            }
        }
    }
}
