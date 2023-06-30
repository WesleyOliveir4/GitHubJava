package com.example.githubjava

import android.app.Application
import com.example.githubjava.di.*
import com.example.githubjava.presentation.home.viewmodel.RepositorioViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
//            androidContext(this@AppApplication)
//            modules(repositorioViewModelModule)

            modules(
                repositoryRepositorioModule,
                repositoryPullRequestModule,
                repositorioViewModelModule
            )
        }
    }
}