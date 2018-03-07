package com.demo.mvvmdemo.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsApi {

    @GET("/?")
    fun getFilms(@Query("apikey") apikey: String, @Query("s") title: String): Single<Films>
}