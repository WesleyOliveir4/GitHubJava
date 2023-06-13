package com.example.githubjava.di

import com.example.githubjava.data.repository.PullRequestRepository
import com.example.githubjava.data.repository.PullRequestRepositoryImpl
import com.example.githubjava.data.repository.RepositorioRepository
import com.example.githubjava.data.repository.RepositorioRepositoryImpl
import org.koin.dsl.module

val repositoryRepositorioModule= module {


    factory<RepositorioRepository>  {
        RepositorioRepositoryImpl()
    }

}