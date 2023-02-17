package com.example.a6monthproject.ui.details

import com.example.a6monthproject.base.BaseViewModel
import com.example.a6monthproject.data.repo.Repository

class PlayListDetailsViewModel(private val repo: Repository) : BaseViewModel() {


    fun getPlayer(playlistId: String) = repo.getPlaylistDetails(playlistId)


}