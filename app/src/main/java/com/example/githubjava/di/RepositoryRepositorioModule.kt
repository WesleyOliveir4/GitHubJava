package com.example.githubjava.di

import com.example.githubjava.domain.repositorio.RepositorioRepository
import com.example.githubjava.data.repository.RepositorioRepositoryImpl
import org.koin.dsl.module

val repositoryRepositorioModule= module {


    factory<RepositorioRepository>  {
        RepositorioRepositoryImpl()
    }

}