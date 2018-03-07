package com.demo.mvvmdemo.model

import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class FilmsNetworkDataSource @Inject constructor(retrofit: Retrofit) {

    private val filmsApi: FilmsApi = retrofit
            .create(FilmsApi::class.java)

    fun searchFilms(filmName: String): Single<Films> {
        return filmsApi.getFilms("1939279b", filmName)
    }
}