package com.example.githubjava.data.repository

import com.example.githubjava.data.repository.SearchRepositorioUseCaseImpl
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertNotNull


class SearchRepositorioUseCaseImplTest {

    @io.mockk.impl.annotations.MockK
    private lateinit var searchRepositorioUseCaseImpl: SearchRepositorioUseCaseImpl

    @Before
    fun setup(){
        searchRepositorioUseCaseImpl = mockk()
    }

    @Test
    fun `should_return_repositorios_when_request_return_success`() = runTest{

        val listRepositorios = mutableListOf<Repositorio>( mockk<Repositorio>())

        // Arrange
        coEvery { searchRepositorioUseCaseImpl.fetchCurrencies("1") } returns listRepositorios

        // Act
       val result = searchRepositorioUseCaseImpl.fetchCurrencies("1")

        // Assert
        assertNotNull(result)
    }
}