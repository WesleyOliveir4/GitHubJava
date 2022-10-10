package com.example.githubjava.dao

import com.example.githubjava.model.Repositorio

class RepositorioDao {
    fun adiciona(repositorio: Repositorio){
        repositorios.add(repositorio)
    }

    fun buscaTodos(): List<Repositorio>{
        return repositorios.toList()
    }

    companion object {
        private val repositorios = mutableListOf<Repositorio>()
    }
}