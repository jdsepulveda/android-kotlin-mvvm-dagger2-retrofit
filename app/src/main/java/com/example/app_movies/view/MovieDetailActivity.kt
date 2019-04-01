package com.example.app_movies.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.example.app_movies.R
import com.example.app_movies.base.App
import com.example.app_movies.model.Movie
import com.example.app_movies.viewModel.SelectedMovieViewModel
import com.example.app_movies.viewModel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import javax.inject.Inject

class MovieDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var selectedMovieViewModel: SelectedMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        App.getAppComponent(this)!!.inject(this)

        val intent = intent
        if (intent.hasExtra("movie")) {
            val movie: Movie = getIntent().getParcelableExtra<Parcelable>("movie") as Movie

            selectedMovieViewModel =
                ViewModelProviders.of(this, viewModelFactory).get(SelectedMovieViewModel::class.java)
            selectedMovieViewModel.setMovieId(movie.id.toString())
            selectedMovieViewModel.restoreFromBundle(savedInstanceState)
            showMovieDetail()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        selectedMovieViewModel.saveToBundle(outState)
        super.onSaveInstanceState(outState)
    }

    private fun showMovieDetail() {
        selectedMovieViewModel.selectedMovie.observe(this, Observer{ movie ->
            val imgPath = "https://image.tmdb.org/t/p/w500" + movie!!.backdropPath
            Picasso.get()
                .load(imgPath)
                .placeholder(R.color.white)
                .into(imgMovieDetail)

            tvTitle.setText(movie.originalTitle)

            var movieGenre = ""
            for (g in movie.genres!!) {
                movieGenre = movieGenre + g.name + " - "
            }
            tvGenre.text = movieGenre
        })
    }
}