package com.example.githubjava.data.model.consultive

import com.example.githubjava.data.models.PullRequests

interface SearchPullRequestUseCase {

    suspend fun consultaPullRequest(
        nomeCriador: String,
        nomeRepositorio: String
    ): MutableList<PullRequests>


}