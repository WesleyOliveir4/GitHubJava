package com.example.githubjava.presentation.repositorio

import android.widget.Button
import android.widget.TextView
import com.example.githubjava.domain.models.OwnerModel
import com.example.githubjava.domain.models.Repositorio
import com.example.githubjava.domain.repositorio.SearchRepositorioUseCase
import com.example.githubjava.presentation.repositorio.viewmodel.RepositorioViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.get
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
class RepositorioViewModelTest {

    private val searchRepositorioUseCase: SearchRepositorioUseCase = mockk()
    private lateinit var repositorioViewModel: RepositorioViewModel

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
    }

    @Test
    fun`should search repositorio is successful`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("nomeTeste","descTeste","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")
        )
        )

        coEvery { searchRepositorioUseCase.fetchCurrencies("1") } returns listRepositorios

//        val result =  repositorioViewModel.consultaRepositorio(1)
//
//
//        assertTrue(result.isNotEmpty())
    }

    @Test
    fun`should search repositorio is successful and empty`()= runTest {
        val listRepositorios = mutableListOf<Repositorio>( Repositorio("","","autorTeste","fotoTeste","10","10",
            OwnerModel("loginTeste","avatarTeste")))
        val btnAnterior : Button = mockk()
        val btnSeguinte : Button = mockk()
        val numeroPagina :TextView =mockk()


        coEvery { searchRepositorioUseCase.fetchCurrencies("1") } returns listRepositorios
        every { repositorioViewModel.configuraPaginacao(btnAnterior,btnSeguinte,numeroPagina) } answers {
            numeroPagina.setText("1")
            every { btnAnterior.callOnClick() } returns true
        }
//        every { btnAnterior.callOnClick() } returns true
//        every { btnSeguinte.callOnClick() } returns true

        numeroPagina.setText("1")

        repositorioViewModel.configuraPaginacao(btnAnterior,btnSeguinte,numeroPagina)
        btnAnterior.callOnClick()
        val result = repositorioViewModel.state.value

        assertNotNull(result)
    }
}