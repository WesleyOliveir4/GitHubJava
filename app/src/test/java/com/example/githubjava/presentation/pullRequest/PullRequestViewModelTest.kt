package com.example.githubjava.presentation.pullRequest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubjava.domain.models.PullRequests
import com.example.githubjava.domain.models.User
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import io.mockk.coEvery
import io.mockk.core.ValueClassSupport.boxedValue
import io.mockk.mockk
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.time.withTimeout
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.Timeout.seconds
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import java.time.Duration
import kotlin.test.assertContains
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.time.Duration.Companion.seconds

class PullRequestViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val searchPullRequestUseCase: SearchPullRequestUseCase = mockk()
    private lateinit var pullRequestViewModel: PullRequestViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    factory { searchPullRequestUseCase }
                    viewModel { PullRequestViewModel(get()) }
                }
            )
        }
        pullRequestViewModel = PullRequestViewModel(searchPullRequestUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
        stopKoin()
    }

    @Test
    fun `should search pullRequest is successful`() = runTest {
        val listPullRequest = mutableListOf<PullRequests>(
            PullRequests(
                "nomeAutorTeste", "tituloTeste", "dataTeste", "bodyTeste",
                User("loginTeste")
            )
        )

        // Arrange
        coEvery {
            searchPullRequestUseCase.fetchCurrencies(
                "criadorTeste",
                "repositorioTeste"
            )
        } returns listPullRequest

        // Act

        pullRequestViewModel.buscandoPullRequests("criadorTeste","repositorioTeste")
        val result = launch {
            pullRequestViewModel.state.value
        }


        // Assert
        assertNotNull(result)
    }
}