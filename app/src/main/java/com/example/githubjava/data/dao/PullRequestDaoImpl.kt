package com.example.githubjava.data.dao

import com.example.githubjava.data.models.PullRequests

class PullRequestDaoImpl : PullRequestDao{

    override fun adicionaPullRequest(pullRequest: PullRequests){
        pullRequests.add(pullRequest)
    }

    override fun buscaTodosPullRequests(): List<PullRequests>{
        return pullRequests.toList()
    }

    override fun removeTodosPullRequests(){
        pullRequests.clear()
    }

    companion object {
        private val pullRequests = mutableListOf<PullRequests>()
    }
}