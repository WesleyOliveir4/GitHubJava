package com.example.githubjava.di

import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.data.repository.SearchPullRequestUseCaseImpl
import org.koin.dsl.module

val repositoryPullRequestModule= module {


    factory<SearchPullRequestUseCase>  {
        SearchPullRequestUseCaseImpl()
    }

}