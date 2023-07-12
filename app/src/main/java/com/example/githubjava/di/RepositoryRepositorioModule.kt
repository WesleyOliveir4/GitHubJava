package com.example.githubjava.di

import com.example.githubjava.domain.repositorio.SearchRepositorioUseCase
import com.example.githubjava.data.repository.SearchRepositorioUseCaseImpl
import org.koin.dsl.module

val repositoryRepositorioModule= module {


    factory<SearchRepositorioUseCase>  {
        SearchRepositorioUseCaseImpl()
    }

}