package com.example.githubjava.di

import com.example.githubjava.data.repository.SearchPullRequestUseCaseImpl
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.presentation.repositorio.RepositorioActivity
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val repositorioViewModelModule = module {

    factory<SearchPullRequestUseCase>  {
        SearchPullRequestUseCaseImpl()
    }

    viewModel { RepositorioViewModel(get()) }

}

internal val loadingRepositorioViewModelModule by lazy { loadKoinModules(repositorioViewModelModule) }

internal fun injectRepositorioViewModelModule() = loadingRepositorioViewModelModule