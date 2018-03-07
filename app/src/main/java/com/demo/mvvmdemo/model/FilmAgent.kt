package com.demo.mvvmdemo.model

import io.reactivex.Single
import javax.inject.Inject

class FilmAgent @Inject constructor(private val filmsNetworkDataSource: FilmsNetworkDataSource) {

    fun searchFilms(filmName: String): Single<Films> = filmsNetworkDataSource.searchFilms(filmName)
}


