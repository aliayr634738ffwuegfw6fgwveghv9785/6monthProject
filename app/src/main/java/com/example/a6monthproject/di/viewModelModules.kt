package com.example.a6monthproject.di

import com.example.a6monthproject.ui.details.PlayListDetailsViewModel
import com.example.a6monthproject.ui.player.PlayerViewModel
import com.example.a6monthproject.ui.playlist.PlayListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { PlayListViewModel(get(), get()) }
    viewModel { PlayListDetailsViewModel(get()) }
    viewModel { PlayerViewModel(get()) }
}