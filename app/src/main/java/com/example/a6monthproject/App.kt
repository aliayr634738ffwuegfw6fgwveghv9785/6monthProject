package com.example.a6monthproject

import android.app.Application
import androidx.room.Room
import com.example.a6monthproject.data.db.AppDataBase
import com.example.a6monthproject.data.repo.Repository
import com.example.a6monthproject.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App:Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(koinModules)
        }


    }
}