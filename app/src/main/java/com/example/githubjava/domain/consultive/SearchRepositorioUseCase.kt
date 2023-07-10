package com.example.githubjava.domain.consultive

import com.example.githubjava.domain.models.Repositorio

interface SearchRepositorioUseCase {

   suspend fun consultaRepositorio(paginaAtual:Int): List<Repositorio>

}