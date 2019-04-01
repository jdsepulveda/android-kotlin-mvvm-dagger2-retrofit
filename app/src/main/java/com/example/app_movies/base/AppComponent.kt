package com.example.app_movies.base

import com.example.app_movies.module.NetworkModule
import com.example.app_movies.module.ViewModelModule
import com.example.app_movies.view.MovieDetailActivity
import com.example.app_movies.view.MoviesActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(moviesActivity: MoviesActivity)
    fun inject(movieDetailActivity: MovieDetailActivity)
}