package com.example.githubjava.presentation.pullRequest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.githubjava.domain.models.PullRequests
import com.example.githubjava.domain.models.User
import com.example.githubjava.domain.pullRequest.SearchPullRequestUseCase
import com.example.githubjava.presentation.pullRequest.state.PullRequestState
import com.example.githubjava.presentation.pullRequest.viewmodel.PullRequestViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import kotlin.test.assertEquals

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
    fun `should_search_pullRequest_when_return_successful`() = runTest {
        val listPullRequest = mutableListOf<PullRequests>(
            PullRequests(
                "nomeAutorTeste", "tituloTeste", "dataTeste", "bodyTeste",
                User("loginTeste")
            )
        )
        val mockLifecycleOwner = mockk<LifecycleOwner>()


        // Arrange
        coEvery {
            searchPullRequestUseCase.fetchCurrencies(
                "criadorTeste",
                "repositorioTeste"
            )
        } answers{listPullRequest}

        every { mockLifecycleOwner.lifecycle } returns LifecycleRegistry(mockLifecycleOwner).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // Act
        pullRequestViewModel.buscandoPullRequests("criadorTeste", "repositorioTeste")

        // Assert
       pullRequestViewModel.state.observe(mockLifecycleOwner) { state ->
            when (state) {
                is PullRequestState.ShowItems -> {
                    assertEquals(
                        listPullRequest[0].tituloPullRequests,
                        state.items[0].tituloPullRequests
                    )
                }
                else -> Log.d("stateHomeActivity", "retornou com erro")
            }
        }

    }

}