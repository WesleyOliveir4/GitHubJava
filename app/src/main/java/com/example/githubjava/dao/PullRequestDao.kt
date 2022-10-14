package com.example.githubjava.dao

import com.example.githubjava.model.PullRequests

class PullRequestDao {
    fun adicionaPullRequest(pullRequest: PullRequests){
        pullRequests.add(pullRequest)
    }

    fun buscaTodosPullRequests(): List<PullRequests>{
        return pullRequests.toList()
    }

    fun removeTodosPullRequests(){
        pullRequests.clear()
    }

    companion object {
        private val pullRequests = mutableListOf<PullRequests>()
    }
}