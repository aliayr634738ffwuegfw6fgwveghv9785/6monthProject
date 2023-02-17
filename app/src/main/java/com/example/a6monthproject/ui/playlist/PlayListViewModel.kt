package com.example.a6monthproject.ui.playlist

import androidx.lifecycle.*
import com.example.a6monthproject.base.BaseViewModel
import com.example.a6monthproject.data.remote.ApiService
import com.example.a6monthproject.data.repo.Repository
import com.example.a6monthproject.model.PlayList

class PlayListViewModel(private val repo: Repository, private val service: ApiService) :
    BaseViewModel() {

    private val setPlaylistLiveData = MutableLiveData<PlayList>()
    private val getPlaylistLiveDataDB = MutableLiveData<Boolean>()
    private val getPlaylistLiveData = MutableLiveData<Int>()


    val getPlayListDB = getPlaylistLiveDataDB.switchMap {
        repo.getPlaylistDB()
    }
    val getPlayList = getPlaylistLiveData.switchMap {
        repo.getPlaylist(it)
    }

    fun setPlaylistDB(playlist: PlayList?) {
        setPlaylistLiveData.postValue(playlist!!)
    }

    fun getPlaylistDB() {
        getPlaylistLiveDataDB.postValue(true)
    }

    fun getPlaylist(maxResult: Int) {
        getPlaylistLiveData.postValue(maxResult)
    }

}