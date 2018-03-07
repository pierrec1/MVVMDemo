package com.demo.mvvmdemo.model

import com.google.gson.annotations.SerializedName

data class Films(@SerializedName("Search") val filmList: List<Film>)

data class Film (

        var Title: String,
        var Year: String,
        var imdbID: String,
        var Type: String,
        var Poster: String
)