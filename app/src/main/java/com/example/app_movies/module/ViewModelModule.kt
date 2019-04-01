package com.example.app_movies.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.app_movies.viewModel.MoviesViewModel
import com.example.app_movies.viewModel.SelectedMovieViewModel
import com.example.app_movies.viewModel.ViewModelFactory
import com.example.app_movies.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SelectedMovieViewModel::class)
    internal abstract fun bindMovieDetailViewModel(viewModel: SelectedMovieViewModel): ViewModel
}