package com.example.littlelemon.application

import android.app.Application
import com.example.littlelemon.data.AppDatabase
import com.example.littlelemon.repository.MenuRepository

class LittleLemonApplication : Application() {

    lateinit var repository: MenuRepository
        private set

    override fun onCreate() {
        super.onCreate()
        // Initialize the Room database
        val dao = AppDatabase.getDatabase(this).menuItemDao()
        // Initialize repository with DAO
        repository = MenuRepository(dao)
    }
}
