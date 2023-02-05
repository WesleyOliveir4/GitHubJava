package com.example.githubjava.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubjava.presentation.home.state.HomeState

class HomeViewModel : ViewModel() {

    private val _state by lazy { MutableLiveData<HomeState>() }
    val state: LiveData<HomeState> = _state


}