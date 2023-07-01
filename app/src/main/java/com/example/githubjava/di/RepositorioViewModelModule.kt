package com.example.githubjava.di

import com.example.githubjava.presentation.repositorio.RepositorioActivity
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositorioViewModelModule = module {


    factory<RepositorioActivity> {
        RepositorioActivity()
    }

    viewModel { RepositorioViewModel(repositorioActivity = get()) }

}