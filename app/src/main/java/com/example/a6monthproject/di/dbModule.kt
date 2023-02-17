package com.example.a6monthproject.di

import android.content.Context
import androidx.room.Room
import com.example.a6monthproject.data.db.AppDataBase
import org.koin.dsl.module

val dbModule = module {
    single { provideDB(get()) }
}

fun provideDB(applicationContext: Context): AppDataBase{
     return Room.databaseBuilder(
        applicationContext,
        AppDataBase::class.java,
        "DB"
    ).build()
}