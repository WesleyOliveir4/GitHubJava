package com.example.githubjava.di

import com.example.githubjava.presentation.home.RepositorioActivity
import com.example.githubjava.presentation.home.viewmodel.RepositorioViewModel
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pullRequestViewModelModule = module {


    factory<PullRequestActivity> {
        PullRequestActivity()
    }

    viewModel { PullRequestViewModel(pullRequestActivity = get()) }

}