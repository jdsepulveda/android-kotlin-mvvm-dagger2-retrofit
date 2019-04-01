package com.example.app_movies.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.app_movies.model.Movie
import com.example.app_movies.model.MovieDBResponse
import com.example.app_movies.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MoviesViewModel
@Inject constructor(private val apiService: APIService) : ViewModel() {
    private val movies = MutableLiveData<List<Movie>>()
    private val moviesLoadError = MutableLiveData<Boolean>()
    private val loading = MutableLiveData<Boolean>()

    private var apiCall: Call<MovieDBResponse>? = null

    val error: LiveData<Boolean>
        get() = moviesLoadError

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        loading.setValue(true)
        apiCall = apiService.getMovies()
        apiCall!!.enqueue(object : Callback<MovieDBResponse> {
            override fun onResponse(call: Call<MovieDBResponse>, response: Response<MovieDBResponse>) {
                moviesLoadError.setValue(false)
                movies.setValue(response.body()!!.movies)
                loading.setValue(false)
                apiCall = null
            }

            override fun onFailure(call: Call<MovieDBResponse>, t: Throwable) {
                moviesLoadError.setValue(true)
                loading.setValue(false)
                apiCall = null
            }
        })
    }

    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    override fun onCleared() {
        if (apiCall != null) {
            apiCall!!.cancel()
            apiCall = null
        }
    }
}