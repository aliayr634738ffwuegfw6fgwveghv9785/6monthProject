package com.example.a6monthproject.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.a6monthproject.App
import com.example.a6monthproject.`object`.Constant
import com.example.a6monthproject.data.db.AppDataBase
import com.example.a6monthproject.data.remote.ApiService
import com.example.a6monthproject.data.remote.network.Resource
import com.example.a6monthproject.model.PlayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class Repository(private val apiService: ApiService, private val db: AppDataBase) {

    fun getPlaylist(maxResult: Int): LiveData<Resource<PlayList>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val max = if (maxResult > Constant.TOTAL_RESULT) {
            Constant.TOTAL_RESULT
        } else maxResult

        val result = apiService.getPlaylist(max = maxResult.toString())
        if (result.isSuccessful) {
            emit(Resource.success(result.body()))
        } else {
            emit(Resource.error(result.message()))
        }
    }

    fun getPlaylistDetails(playlistId: String): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())


            val result = apiService.getPlaylistDetails(playlistId = playlistId)

            if (result.isSuccessful) {
                emit(Resource.success(result.body()))
            } else {
                emit(Resource.error(result.message()))
            }
        }


    fun getPlayer(videoID: String): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val result = apiService.getPlayer(videoId = videoID)

            if (result.isSuccessful) {
                emit(Resource.success(result.body()))
            } else {
                emit(Resource.error(result.message()))
            }
        }


    fun setPlaylistDB(playlist: PlayList?): LiveData<Resource<Boolean>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            if (playlist != null) {
                db.dao().insert(playlist)
                emit(Resource.success(true))
            } else {
                emit(Resource.error("Data is null"))

            }
        }

    fun getPlaylistDB(): LiveData<Resource<PlayList>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())
            val result = db.dao().getPlaylistFromDao()

            emit(Resource.success(result))


        }


}



