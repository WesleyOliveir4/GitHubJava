package com.example.githubjava.data.dao

import com.example.githubjava.data.model.Repositorio

class RepositorioDao {

    fun adicionaRepositorio(repositorio: Repositorio){
        repositorios.add(repositorio)
    }

    fun buscaTodosRepositorios(): List<Repositorio>{
        return repositorios.toList()
    }

    fun removeTodosRepositorios(){
        repositorios.clear()
    }

    companion object {
        private val repositorios = mutableListOf<Repositorio>()
    }


}