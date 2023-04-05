package com.example.githubjava.data.dao

import com.example.githubjava.data.models.Repositorio

interface RepositorioDao {

    fun adicionaRepositorio(repositorio: Repositorio)

    fun buscaTodosRepositorios(): List<Repositorio>

    fun removeTodosRepositorios()


}