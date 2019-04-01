package com.example.app_movies.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.app_movies.R
import com.example.app_movies.adapter.MovieListAdapter
import com.example.app_movies.base.App
import com.example.app_movies.listener.MovieSelectedListener
import com.example.app_movies.model.Movie
import com.example.app_movies.viewModel.MoviesViewModel
import com.example.app_movies.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_movies.*
import javax.inject.Inject

class MoviesActivity : AppCompatActivity(), MovieSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var moviesViewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)

        App.getAppComponent(this)!!.inject(this)

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory).get(MoviesViewModel::class.java)

        rvMoviesList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rvMoviesList.adapter = MovieListAdapter(moviesViewModel, this, this)
        rvMoviesList.layoutManager = LinearLayoutManager(this)
        observeMoviesViewModel()
    }

    override fun onMovieSelected(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    private fun observeMoviesViewModel() {
        moviesViewModel.getMovies().observe(this, Observer{ repos ->
            if (repos != null) {
                rvMoviesList.visibility = View.VISIBLE
            }
        })

        moviesViewModel.error.observe(this, Observer{ isError ->
            if (isError!!) {
                tvErrorMsg.visibility = View.VISIBLE
                rvMoviesList.visibility = View.GONE
                tvErrorMsg.setText(R.string.api_error_movies)
            } else {
                tvErrorMsg.visibility = View.GONE
                tvErrorMsg.text = null
            }
        })

        moviesViewModel.getLoading().observe(this, Observer{ isLoading ->
            loadingMovies.visibility = if (isLoading!!) View.VISIBLE else View.GONE
            if (isLoading) {
                tvErrorMsg.visibility = View.GONE
                rvMoviesList.visibility = View.GONE
            }
        })
    }
}