package com.example.githubjava.presentation.repositorio


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.domain.repositorio.SearchRepositorioUseCase
import com.example.githubjava.presentation.repositorio.state.HomeState
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
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

class RepositorioViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val searchRepositorioUseCase: SearchRepositorioUseCase = mockk()
    private lateinit var repositorioViewModel: RepositorioViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup(){
            startKoin {
                modules(
                    module{
                        factory { searchRepositorioUseCase }
                        viewModel { RepositorioViewModel(get()) }
                    }
                )
            }
        repositorioViewModel = RepositorioViewModel(searchRepositorioUseCase)
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher]
        stopKoin()
        mainThreadSurrogate.close()
    }

    @Test
    fun`should_search_repositorio_page_when_return_successful`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")
        )
        )
        val mockLifecycleOwner = mockk<LifecycleOwner>()

        // Arrange
            coEvery { searchRepositorioUseCase.fetchCurrencies("1") } answers {
                listRepositorios
            }

            every { mockLifecycleOwner.lifecycle } returns LifecycleRegistry(mockLifecycleOwner).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
            }

        // Act
        repositorioViewModel.configuraPaginacao(false)

        // Assert
        repositorioViewModel.state.observe(mockLifecycleOwner) { state ->
            when (state) {
                is HomeState.ShowItems -> {
                    assertEquals(
                        listRepositorios[0],
                        state.items[0]
                    )
                }
            }
        }
    }

    @Test
    fun`should_search_repositorio_next_pages_when_return_successful`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")
        )
        )
        val mockLifecycleOwner = mockk<LifecycleOwner>()

        // Arrange
        coEvery { searchRepositorioUseCase.fetchCurrencies("2") } answers {
            listRepositorios
        }

        every { mockLifecycleOwner.lifecycle } returns LifecycleRegistry(mockLifecycleOwner).apply {
            handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
            handleLifecycleEvent(Lifecycle.Event.ON_START)
            handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        }

        // Act
        repositorioViewModel.configuraPaginacao(true)

        // Assert
        repositorioViewModel.state.observe(mockLifecycleOwner) { state ->
            when (state) {
                is HomeState.ShowItems -> {
                    assertEquals(
                        listRepositorios[0],
                        state.items[0]
                    )
                }
            }
        }
    }
}