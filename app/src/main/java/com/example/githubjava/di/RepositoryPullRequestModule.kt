package com.example.githubjava.di

import com.example.githubjava.data.repository.PullRequestRepository
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import org.koin.dsl.module

val repositoryPullRequestModule= module {


    single<PullRequestRepository>  {
        PullRequestRepositoryImpl()
    }

}