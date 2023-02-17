package com.example.a6monthproject.ui.player

import com.example.a6monthproject.base.BaseViewModel
import com.example.a6monthproject.data.repo.Repository

class PlayerViewModel(private val repo: Repository) : BaseViewModel() {


    fun getPlayer(videoId: String) = repo.getPlayer(videoId)
}