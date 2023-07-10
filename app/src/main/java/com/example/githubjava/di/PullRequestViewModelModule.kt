package com.example.githubjava.di

import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pullRequestViewModelModule = module {

    viewModel { PullRequestViewModel(get()) }

}