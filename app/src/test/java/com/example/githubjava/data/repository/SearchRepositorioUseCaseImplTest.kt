package com.example.githubjava.data.repository

import androidx.lifecycle.viewmodel.savedstate.R
import com.example.githubjava.data.repository.SearchRepositorioUseCaseImpl
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import io.mockk.MockK
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.`should be instance of`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.assertThrows
import retrofit2.Response
import java.util.Optional.empty
import java.util.function.Predicate.not
import kotlin.test.*


class SearchRepositorioUseCaseImplTest {

    @io.mockk.impl.annotations.MockK
    private lateinit var searchRepositorioUseCaseImpl: SearchRepositorioUseCaseImpl

    @Before
    fun setup(){
        searchRepositorioUseCaseImpl = mockk()
    }

    @Test
    fun `should_return_repositorios_when_request_return_success`() = runTest{

        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",OwnerModel("loginTeste","avatarTeste")))

        // Arrange
        coEvery { searchRepositorioUseCaseImpl.fetchCurrencies("1") } returns listRepositorios

        // Act
       val result : MutableList<Repositorio> = searchRepositorioUseCaseImpl.fetchCurrencies("1")

        // Assert
        assertNotEquals(result[0].nomeRepositorio,"")
    }

    @Test
    fun `should_return_repositorios_when_request_return_nome_repo_empty`() = runTest{

        val listRepositorios = mutableListOf<Repositorio>( Repositorio("","descTeste","autorTeste","fotoTeste","10","10",OwnerModel("loginTeste","avatarTeste")))

        // Arrange
        coEvery { searchRepositorioUseCaseImpl.fetchCurrencies("1") } returns listRepositorios

        // Act
        val result : MutableList<Repositorio> = searchRepositorioUseCaseImpl.fetchCurrencies("1")

        // Assert
        Assert.assertEquals(result[0].nomeRepositorio, "")

    }

}

