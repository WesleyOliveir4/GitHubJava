package com.example.githubjava.di

import com.example.githubjava.data.model.consultive.PullRequestConsultive
import org.koin.dsl.module

val pullRequestConsultiveModule = module {


    single{
        PullRequestConsultive(
           pullRequestRepository = get()
        )
    }

}