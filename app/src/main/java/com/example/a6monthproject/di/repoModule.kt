package com.example.a6monthproject.di

import com.example.a6monthproject.data.repo.Repository
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get(), get()) }
}