package com.example.app_movies.networking

import com.example.app_movies.model.MovieDBResponse
import com.example.app_movies.model.MovieDetail

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    companion object {
        const val ApiKey = "Personal Api Key"
    }

    @GET("movie/popular?api_key=$ApiKey")
    fun getMovies(): Call<MovieDBResponse>

    @GET("movie/{movie_id}?api_key=$ApiKey&language=en-US")
    fun getMovieDetail(@Path("movie_id") movieId: String): Call<MovieDetail>
}