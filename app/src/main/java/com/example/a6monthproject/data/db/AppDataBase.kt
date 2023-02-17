package com.example.a6monthproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.a6monthproject.model.PlayList

@TypeConverters(TypeConverter::class)
@Database(entities = [PlayList::class], version = 1)
abstract class AppDataBase: RoomDatabase() {
    abstract fun dao(): PlaylistDao
}