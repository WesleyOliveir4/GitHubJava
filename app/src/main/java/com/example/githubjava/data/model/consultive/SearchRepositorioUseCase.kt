package com.example.githubjava.data.model.consultive

import com.example.githubjava.data.models.Repositorio

interface SearchRepositorioUseCase {

   suspend fun consultaRepositorio(paginaAtual:Int): List<Repositorio>

}