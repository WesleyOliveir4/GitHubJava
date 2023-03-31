package com.example.githubjava.presentation.state

import com.example.githubjava.data.models.PullRequests
import com.example.githubjava.data.models.Repositorio

sealed interface PullRequestState {

    object Loading : PullRequestState

    data class ShowItems( val items: MutableList<PullRequests>):PullRequestState

}