package com.example.githubjava.domain.pullRequest

import com.example.githubjava.domain.models.PullRequests

interface SearchPullRequestUseCase {

    suspend fun fetchCurrencies(criador: String, repositorio : String): MutableList<PullRequests>

}