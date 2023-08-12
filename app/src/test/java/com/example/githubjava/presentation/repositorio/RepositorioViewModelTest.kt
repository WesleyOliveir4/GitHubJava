package com.example.githubjava.presentation.repositorio

import android.util.Log
import android.widget.Button
import android.widget.TextView
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
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
class RepositorioViewModelTest {

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

            coEvery { searchRepositorioUseCase.fetchCurrencies("1") } answers {
                listRepositorios
            }

        repositorioViewModel.consultaApiGit()
        repositorioViewModel.buscandoRepositorios(0)

    }

    @Test
    fun`should search repositorio is successful and empty`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")
        )
        )

        coEvery { searchRepositorioUseCase.fetchCurrencies("1") } answers {
            listRepositorios
        }

        repositorioViewModel.consultaApiGit()
        repositorioViewModel.buscandoRepositorios(0)
    }
}