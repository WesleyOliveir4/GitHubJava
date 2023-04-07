package com.example.githubjava.data.dao

import com.example.githubjava.data.models.PullRequests

interface PullRequestDao {

    fun adicionaPullRequest(pullRequest: PullRequests)

    fun buscaTodosPullRequests(): List<PullRequests>

    fun removeTodosPullRequests()

}