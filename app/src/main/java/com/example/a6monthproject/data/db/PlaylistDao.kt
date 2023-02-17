package com.example.a6monthproject.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a6monthproject.model.PlayList

@Dao
interface PlaylistDao {

    @Insert
    suspend fun insert(playList: PlayList)

    @Query("SELECT * FROM playlist")
    fun getPlaylistFromDao(): PlayList
}