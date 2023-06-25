package com.example.githubjava

import android.app.Application
import com.example.githubjava.di.*
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