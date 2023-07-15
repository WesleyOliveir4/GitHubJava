package com.example.githubjava.di

import com.example.githubjava.data.repository.SearchPullRequestUseCaseImpl
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val pullRequestViewModelModule = module {

    factory<SearchPullRequestUseCase>  {
        SearchPullRequestUseCaseImpl()
    }

    viewModel { PullRequestViewModel(get()) }

}
internal val loadingPullRequestViewModelModule by lazy { loadKoinModules(pullRequestViewModelModule) }

internal fun injectPullRequestViewModelModule() = loadingPullRequestViewModelModule