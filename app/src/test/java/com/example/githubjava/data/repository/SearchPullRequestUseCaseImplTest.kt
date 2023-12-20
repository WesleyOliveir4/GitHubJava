package com.example.githubjava.data.repository

import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.PullRequests
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.domain.models.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertIsNot
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

class SearchPullRequestUseCaseImplTest {
    @io.mockk.impl.annotations.MockK
    private lateinit var searchPullRequestUseCaseImpl: SearchPullRequestUseCaseImpl

    @Before
    fun setup(){
        searchPullRequestUseCaseImpl = mockk()
    }

    @Test
    fun `should_return_pullRequest_when_request_return_success`() = runTest{

        val listPullRequest = mutableListOf<PullRequests>( PullRequests("nomeAutorTeste","tituloTeste","dataTeste","bodyTeste",
            User("loginTeste")
        ))


        // Arrange
        coEvery { searchPullRequestUseCaseImpl.fetchCurrencies("criadorTeste","repositorioTeste") } returns listPullRequest

        // Act
        val result : MutableList<PullRequests> = searchPullRequestUseCaseImpl.fetchCurrencies("criadorTeste","repositorioTeste")

        // AssertlistPullRequest
        assertEquals(listPullRequest, result)
    }

    @Test
    fun `should_return_pullRequest_when_request_return_empty`() = runTest{

        val listPullRequest = mutableListOf<PullRequests>( PullRequests("","tituloTeste","dataTeste","bodyTeste",
            User("loginTeste")
        ))


        // Arrange
        coEvery { searchPullRequestUseCaseImpl.fetchCurrencies("criadorTeste","repositorioTeste") } returns listPullRequest

        // Act
        val result : MutableList<PullRequests> = searchPullRequestUseCaseImpl.fetchCurrencies("criadorTeste","repositorioTeste")

        // Assert
        assertEquals(result[0].nomeAutorPullrequests, "")
    }
}