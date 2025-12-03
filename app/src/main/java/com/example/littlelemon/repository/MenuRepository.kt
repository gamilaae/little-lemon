package com.example.littlelemon.repository

import com.example.littlelemon.data.MenuItemDao
import com.example.littlelemon.data.MenuItemEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import com.example.littlelemon.network.fetchMenuFromNetwork
import com.example.littlelemon.network.toEntityList
import kotlinx.coroutines.withContext

class MenuRepository(private val dao: MenuItemDao) {

    val menuItems: Flow<List<MenuItemEntity>> = dao.getMenuItems()

    suspend fun insertMenuItems(items: List<MenuItemEntity>) {
        withContext(Dispatchers.IO) {
            dao.insertMenuItems(items)
        }
    }

    suspend fun refreshMenuFromNetwork(category: String? = null) {
        withContext(Dispatchers.IO) {
            val networkMenu = fetchMenuFromNetwork(category).toEntityList()
            insertMenuItems(networkMenu)
        }
    }
}
