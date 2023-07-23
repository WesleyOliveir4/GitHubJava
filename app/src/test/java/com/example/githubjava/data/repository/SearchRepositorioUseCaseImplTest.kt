package com.example.githubjava.data.repository

import com.example.githubjava.data.repository.SearchRepositorioUseCaseImpl
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class SearchRepositorioUseCaseImplTest {

    private lateinit var searchRepositorioUseCaseImpl: SearchRepositorioUseCaseImpl

    @Before
    fun setup(){
        searchRepositorioUseCaseImpl = SearchRepositorioUseCaseImpl()
    }

    @Test
    fun `should_return_repositorios_when_request_return_success`() = runTest{

        val listRepositorios = mutableListOf<Repositorio>( Repositorio("","","","","","", owner = OwnerModel("","")))

        // Arrange
        coEvery { searchRepositorioUseCaseImpl.fetchCurrencies("1") }
        
        // Act

        // Assert

    }
}