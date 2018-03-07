package com.demo.mvvmdemo.model

import com.demo.mvvmdemo.model.FilmAgent
import com.demo.mvvmdemo.model.Films
import io.reactivex.Single
import javax.inject.Inject

open class SearchFilmsUseCase @Inject constructor(private val filmAgent: FilmAgent) {

    open fun searchByName(filmName: String): Single<Films> = filmAgent.searchFilms(filmName)
}


