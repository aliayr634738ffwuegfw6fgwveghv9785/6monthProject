package com.example.a6monthproject.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
}