package com.example.githubjava.data.model.consultive

import com.example.githubjava.data.models.Repositorio

interface SearchRepositorioUseCase {

   fun consultaApiGit(paginaAtual:Int): List<Repositorio>

//    fun buscandoRepositorios(pageReceived: Int): List<Repositorio>

}