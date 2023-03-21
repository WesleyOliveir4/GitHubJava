package com.example.githubjava.presentation.state

import com.example.githubjava.data.models.Repositorio

sealed interface HomeState {

    object Loading : HomeState

    data class ShowItems( val items: MutableList<Repositorio>):HomeState

}