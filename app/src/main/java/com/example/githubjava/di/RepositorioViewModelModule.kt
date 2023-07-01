package com.example.githubjava.di

import com.example.githubjava.data.models.OwnerModel
import com.example.githubjava.data.models.Repositorio
import com.example.githubjava.presentation.home.RepositorioActivity
import com.example.githubjava.presentation.home.viewmodel.RepositorioViewModel
import com.example.githubjava.ui.adapter.ListaRepositoriosAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositorioViewModelModule = module {


    factory<RepositorioActivity> {
        RepositorioActivity()
    }

    viewModel { RepositorioViewModel(repositorioActivity = get()) }

}