package com.example.githubjava.data.dao

import com.example.githubjava.data.model.PullRequests

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