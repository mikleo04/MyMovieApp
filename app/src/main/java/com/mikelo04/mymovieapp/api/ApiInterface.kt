package com.mikelo04.mymovieapp.api

import com.mikelo04.mymovieapp.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/3/movie/popular?api_key=20d99a4a865b93886876c788014c9983")
    fun getMovieList(): Call<MovieResponse>
}