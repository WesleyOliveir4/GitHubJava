package com.example.githubjava.presentation.pullRequest.state

import com.example.githubjava.domain.models.PullRequests

sealed interface PullRequestState {

    object Loading : PullRequestState

    data class ShowItems( val items: MutableList<PullRequests>): PullRequestState

}