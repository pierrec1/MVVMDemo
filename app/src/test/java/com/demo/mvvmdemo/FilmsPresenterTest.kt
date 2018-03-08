package com.demo.mvvmdemo

import com.demo.mvvmdemo.model.Film
import com.demo.mvvmdemo.model.Films
import com.demo.mvvmdemo.model.SearchFilmsUseCase
import com.nhaarman.mockito_kotlin.any
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*


class FilmsPresenterTest {

    @Mock
    lateinit var mockSearchFilmsUseCase: SearchFilmsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
    }

    @Test
    fun loadUser_showsUserList_whenUseCaseReturnsList() {
        Mockito.`when`(mockSearchFilmsUseCase.searchByName(any())).thenReturn(Single.just(makeAnyUserList()))
        val filmsPresenter = FilmsPresenter(mockSearchFilmsUseCase)
        val view = mock(FilmsPresenter.View::class.java)
        filmsPresenter.setView(view)

        filmsPresenter.searchForFilms()

        verify(view).showFilmList(ArgumentMatchers.any())
    }

    private fun makeAnyUserList(): Films {
        val films = ArrayList<Film>()
        for (i in 0..4) {
            val user = Film("title_" + i, "Year_" + i, "id_" + i, "Type_" + i, "Poster_" +i);
            films.add(user)
        }
        return Films(films)
    }
}