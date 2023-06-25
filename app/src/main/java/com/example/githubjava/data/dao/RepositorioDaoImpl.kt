package com.example.githubjava.data.dao

import com.example.githubjava.data.models.Repositorio

class RepositorioDaoImpl() : RepositorioDao {


    override fun adicionaRepositorio(repositorio: Repositorio) {
        repositorios.add(repositorio)
    }

    override fun buscaTodosRepositorios(): List<Repositorio> {
        return repositorios.toList()
    }

    override fun removeTodosRepositorios() {
        repositorios.clear()
    }

    companion object {
        private val repositorios = mutableListOf<Repositorio>()
    }

}