package com.example.githubjava.presentation.pullRequest.state

import com.example.githubjava.data.models.PullRequests

sealed interface PullRequestState {

    object Loading : PullRequestState

    data class ShowItems( val items: MutableList<PullRequests>): PullRequestState

}