package com.example.app_movies.adapter

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.app_movies.R
import com.example.app_movies.listener.MovieSelectedListener
import com.example.app_movies.model.Movie
import com.example.app_movies.viewModel.MoviesViewModel
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MovieListAdapter(viewModel: MoviesViewModel, lifecycleOwner: LifecycleOwner,
    private val movieSelectedListener: MovieSelectedListener) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private val movies = ArrayList<Movie>()

    init {
        viewModel.getMovies().observe(lifecycleOwner, Observer{ movie ->
            movies.clear()
            if (movie != null) {
                movies.addAll(movie)
            }
            notifyDataSetChanged()
        })
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie_list_item, parent, false)
        return MovieViewHolder(view, movieSelectedListener)
    }

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, i: Int) {
        movieViewHolder.bind(movies[i])
    }

    override fun getItemId(position: Int): Long {
        return movies[position].id!!
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MovieViewHolder(itemView: View, movieSelectedListener: MovieSelectedListener) :
        RecyclerView.ViewHolder(itemView) {

        private var tvMovieTitle = itemView.findViewById<TextView>(R.id.tvMovieTitle)
        private var imgMovie = itemView.findViewById<ImageView>(R.id.imgMovie)

        private var movie: Movie? = null

        init {
            itemView.setOnClickListener { v ->
                if (movie != null) {
                    movieSelectedListener.onMovieSelected(movie!!)
                }
            }
        }

        fun bind(movie: Movie) {
            this.movie = movie
            tvMovieTitle.setText(movie.title)
            val imgPath = "https://image.tmdb.org/t/p/w500" + movie.backdropPath
            Picasso.get()
                .load(imgPath)
                .placeholder(R.color.white)
                .into(imgMovie)
        }
    }
}