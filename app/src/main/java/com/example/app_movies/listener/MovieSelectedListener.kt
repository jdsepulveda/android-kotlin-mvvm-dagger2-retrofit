package com.example.app_movies.listener

import com.example.app_movies.model.Movie

interface MovieSelectedListener {
    fun onMovieSelected(movie: Movie)
}