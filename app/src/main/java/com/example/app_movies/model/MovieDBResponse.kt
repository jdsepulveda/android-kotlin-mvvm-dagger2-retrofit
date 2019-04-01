package com.example.app_movies.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDBResponse : Parcelable {
    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalMovies: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null

    protected constructor(`in`: Parcel) {
        this.page = `in`.readValue(Int::class.java.classLoader) as Int
        this.totalMovies = `in`.readValue(Int::class.java.classLoader) as Int
        this.totalPages = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.movies, com.example.app_movies.model.Movie::class.java.getClassLoader())
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(page)
        dest.writeValue(totalMovies)
        dest.writeValue(totalPages)
        dest.writeList(movies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDBResponse> = object : Parcelable.Creator<MovieDBResponse> {
            override fun createFromParcel(`in`: Parcel): MovieDBResponse {
                return MovieDBResponse(`in`)
            }

            override fun newArray(size: Int): Array<MovieDBResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}