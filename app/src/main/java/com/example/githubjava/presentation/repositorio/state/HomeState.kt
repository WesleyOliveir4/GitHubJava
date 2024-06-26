package com.example.githubjava.presentation.repositorio.state

import com.example.githubjava.domain.models.Repositorio

sealed interface HomeState {

    data class ShowItems( val items: MutableList<Repositorio>): HomeState

}