package com.example.githubjava.di

import com.example.githubjava.data.model.consultive.PullRequestConsultive
import com.example.githubjava.presentation.pullRequest.PullRequestActivity
import com.example.githubjava.presentation.viewmodel.PullRequestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pullRequestConsultiveModule = module {


    single{
        PullRequestConsultive(
           pullRequestRepository = get()
        )
    }

}