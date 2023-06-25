package com.example.githubjava.di

import com.example.githubjava.data.model.consultive.RepositorioConsultive
import org.koin.dsl.module

val repositorioConsultiveModule = module {


    single {
        RepositorioConsultive(
            repositorioRepository = get()
        )
    }

}