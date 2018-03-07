package com.demo.mvvmdemo.dagger

import com.demo.mvvmdemo.FilmsPresenter
import com.demo.mvvmdemo.model.FilmAgent
import com.demo.mvvmdemo.model.FilmsNetworkDataSource
import com.demo.mvvmdemo.model.SearchFilmsUseCase
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class FilmsModule {

    val BASE_URL = "http://www.omdbapi.com"

    @Provides
    @Singleton
    fun provideFilmsPresenter(searchFilmsUseCase: SearchFilmsUseCase): FilmsPresenter = FilmsPresenter(searchFilmsUseCase)

    @Provides
    @Singleton
    fun provideSearchFilmsUseCase(filmAgent: FilmAgent): SearchFilmsUseCase =
            SearchFilmsUseCase(filmAgent)

    @Provides
    @Singleton
    fun provideFilmAgent(filmsNetworkDataSource: FilmsNetworkDataSource): FilmAgent =
            FilmAgent(filmsNetworkDataSource)

    @Provides
    @Singleton
    fun provideFilmsNetworkDataSource(): FilmsNetworkDataSource {
        return FilmsNetworkDataSource(provideRetrofit())
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}