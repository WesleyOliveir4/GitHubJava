package com.example.githubjava

import android.app.Application
import com.example.githubjava.di.*
import com.example.githubjava.presentation.home.HomeActivity
import com.example.githubjava.presentation.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                repositoryRepositorioModule,
                repositoryPullRequestModule,
                repositorioConsultiveModule,
                pullRequestConsultiveModule

            )
        }
    }
}