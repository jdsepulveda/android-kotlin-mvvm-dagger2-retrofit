package com.example.app_movies.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Build
import android.os.Bundle
import android.util.Log
import com.example.app_movies.model.MovieDetail
import com.example.app_movies.networking.APIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject

class SelectedMovieViewModel @Inject
constructor(private val apiService: APIService) : ViewModel() {
    private val movieSelected = MutableLiveData<MovieDetail>()
    private val movieId = MutableLiveData<String>()
    private var apiCall: Call<MovieDetail>? = null

    val selectedMovie: LiveData<MovieDetail>
        get() = movieSelected

    fun setSelectMovie(movieDetail: MovieDetail) {
        movieSelected.setValue(movieDetail)
    }

    fun setMovieId(movie_Id: String) {
        movieId.setValue(movie_Id + "")
    }

    fun saveToBundle(outState: Bundle) {
        if (movieSelected.getValue() != null) {
            outState.putString("movie_details", movieSelected.getValue()!!.id.toString())
        }
    }

    fun restoreFromBundle(savedInstanceState: Bundle?) {
        if (movieSelected.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    loadMovie(Objects.requireNonNull(savedInstanceState.getString("repo_details")))
                } else {
                    loadMovie(savedInstanceState.getString("repo_details"))
                }
            } else {
                loadMovie(movieId.value)
            }
        }
    }

    private fun loadMovie(movieId: String?) {
        apiCall = apiService.getMovieDetail(movieId!!)
        apiCall!!.enqueue(object : Callback<MovieDetail> {
            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {
                movieSelected.setValue(response.body())
                apiCall = null
            }

            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Log.e(javaClass.simpleName, "Error getting movie details", t)
                apiCall = null
            }
        })
    }

    override fun onCleared() {
        if (apiCall != null) {
            apiCall!!.cancel()
            apiCall = null
        }
    }
}