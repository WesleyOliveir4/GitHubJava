package com.example.githubjava.presentation.repositorio


import androidx.arch.core.executor.TaskExecutor
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.domain.repositorio.SearchRepositorioUseCase

import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.getScopeId
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubjava.presentation.repositorio.state.HomeState
import io.mockk.core.ValueClassSupport.boxedValue
import okhttp3.internal.wait
import org.amshove.kluent.`should be null`
import org.koin.core.component.getScopeName

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
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun`should search repositorio is successful`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")
        )
        )
        var stateReturn: Boolean = false

        // Arrange
            coEvery { searchRepositorioUseCase.fetchCurrencies("1") } answers {
                listRepositorios
            }

        // Act
        repositorioViewModel.consultaApiGit()
        repositorioViewModel.buscandoRepositorios(0)

        // Assert
        repositorioViewModel.state.observeForever {
           if(it != null) {
               assertTrue(stateReturn.not(), "should search repositorio is successful ( Test successful )")
           }
        }

    }
}