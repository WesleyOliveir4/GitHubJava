package com.example.githubjava.domain.repositorio

import com.example.githubjava.domain.models.Repositorio

interface SearchRepositorioUseCase {

    suspend fun fetchCurrencies(page: String): MutableList<Repositorio>

}