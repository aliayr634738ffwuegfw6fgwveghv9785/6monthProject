package com.example.a6monthproject.data.remote

import com.example.a6monthproject.BuildConfig
import com.example.a6monthproject.`object`.Constant
import com.example.a6monthproject.model.PlayList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    suspend fun getPlaylist(
        @Query("part") part: String = Constant.PART,
        @Query("channelId") channelId: String = Constant.CHANNEL_ID,
        @Query("maxResults") max: String = Constant.MAX_RESULTS,
        @Query("key") key: String = BuildConfig.API_KEY,

        ): Response<PlayList>

    @GET("videos")
    suspend fun getPlayer(
        @Query("part") part: String = Constant.PART_TWO,
        @Query("id") videoId: String = "",
        @Query("key") key: String = BuildConfig.API_KEY,

        ): Response<PlayList>


    @GET("playlistItems")
    suspend fun getPlaylistDetails(
        @Query("part") part: String = Constant.PART,
        @Query("playlistId") playlistId: String = "",
        @Query("maxResults") max: String = Constant.MAX_RESULTS,
        @Query("key") key: String = BuildConfig.API_KEY

    ): Response<PlayList>
}