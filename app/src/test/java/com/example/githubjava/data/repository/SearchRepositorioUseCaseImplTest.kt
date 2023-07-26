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


class SearchRepositorioUseCaseImplTest {

    @io.mockk.impl.annotations.MockK
    private lateinit var searchRepositorioUseCaseImpl: SearchRepositorioUseCaseImpl
    @io.mockk.impl.annotations.MockK
    private lateinit var page: String

    @Before
    fun setup(){
        searchRepositorioUseCaseImpl = SearchRepositorioUseCaseImpl()
        page = mockk("1")
    }

    @Test
    fun `should_return_repositorios_when_request_return_success`() = runTest{

        val listRepositorios = mutableListOf<Repositorio>( mockk<Repositorio>())

        // Arrange
        coEvery { searchRepositorioUseCaseImpl.fetchCurrencies(page) } returns listRepositorios

        // Act
        searchRepositorioUseCaseImpl.fetchCurrencies(page)

        // Assert

    }
}