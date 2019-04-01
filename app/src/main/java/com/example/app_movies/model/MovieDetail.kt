package com.example.app_movies.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDetail : Parcelable {
    @SerializedName("adult")
    @Expose
    var adult: Boolean? = null
    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null
    @SerializedName("belongs_to_collection")
    @Expose
    var belongsToCollection: Any? = null
    @SerializedName("budget")
    @Expose
    var budget: Int? = null
    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null
    @SerializedName("homepage")
    @Expose
    var homepage: String? = null
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("imdb_id")
    @Expose
    var imdbId: String? = null
    @SerializedName("original_language")
    @Expose
    var originalLanguage: String? = null
    @SerializedName("original_title")
    @Expose
    var originalTitle: String? = null
    @SerializedName("overview")
    @Expose
    var overview: String? = null
    @SerializedName("popularity")
    @Expose
    var popularity: Double? = null
    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null
    @SerializedName("production_companies")
    @Expose
    var productionCompanies: List<ProductionCompany>? = null
    @SerializedName("production_countries")
    @Expose
    var productionCountries: List<ProductionCountry>? = null
    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null
    @SerializedName("revenue")
    @Expose
    var revenue: Int? = null
    @SerializedName("runtime")
    @Expose
    var runtime: Int? = null
    @SerializedName("spoken_languages")
    @Expose
    var spokenLanguages: List<SpokenLanguage>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("tagline")
    @Expose
    var tagline: String? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("video")
    @Expose
    var video: Boolean? = null
    @SerializedName("vote_average")
    @Expose
    var voteAverage: Double? = null
    @SerializedName("vote_count")
    @Expose
    var voteCount: Int? = null

    protected constructor(`in`: Parcel) {
        this.adult = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        this.backdropPath = `in`.readValue(String::class.java.classLoader) as String
        this.belongsToCollection = `in`.readValue(Any::class.java.classLoader) as Any
        this.budget = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.genres, com.example.app_movies.model.Genre::class.java.getClassLoader())
        this.homepage = `in`.readValue(String::class.java.classLoader) as String
        this.id = `in`.readValue(Int::class.java.classLoader) as Int
        this.imdbId = `in`.readValue(String::class.java.classLoader) as String
        this.originalLanguage = `in`.readValue(String::class.java.classLoader) as String
        this.originalTitle = `in`.readValue(String::class.java.classLoader) as String
        this.overview = `in`.readValue(String::class.java.classLoader) as String
        this.popularity = `in`.readValue(Double::class.java.classLoader) as Double
        this.posterPath = `in`.readValue(String::class.java.classLoader) as String
        `in`.readList(
            this.productionCompanies,
            com.example.app_movies.model.ProductionCompany::class.java.getClassLoader()
        )
        `in`.readList(
            this.productionCountries,
            com.example.app_movies.model.ProductionCountry::class.java.getClassLoader()
        )
        this.releaseDate = `in`.readValue(String::class.java.classLoader) as String
        this.revenue = `in`.readValue(Int::class.java.classLoader) as Int
        this.runtime = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.spokenLanguages, com.example.app_movies.model.SpokenLanguage::class.java.getClassLoader())
        this.status = `in`.readValue(String::class.java.classLoader) as String
        this.tagline = `in`.readValue(String::class.java.classLoader) as String
        this.title = `in`.readValue(String::class.java.classLoader) as String
        this.video = `in`.readValue(Boolean::class.java.classLoader) as Boolean
        this.voteAverage = `in`.readValue(Double::class.java.classLoader) as Double
        this.voteCount = `in`.readValue(Int::class.java.classLoader) as Int
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(adult)
        dest.writeValue(backdropPath)
        dest.writeValue(belongsToCollection)
        dest.writeValue(budget)
        dest.writeList(genres)
        dest.writeValue(homepage)
        dest.writeValue(id)
        dest.writeValue(imdbId)
        dest.writeValue(originalLanguage)
        dest.writeValue(originalTitle)
        dest.writeValue(overview)
        dest.writeValue(popularity)
        dest.writeValue(posterPath)
        dest.writeList(productionCompanies)
        dest.writeList(productionCountries)
        dest.writeValue(releaseDate)
        dest.writeValue(revenue)
        dest.writeValue(runtime)
        dest.writeList(spokenLanguages)
        dest.writeValue(status)
        dest.writeValue(tagline)
        dest.writeValue(title)
        dest.writeValue(video)
        dest.writeValue(voteAverage)
        dest.writeValue(voteCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDetail> = object : Creator<MovieDetail> {
            override fun createFromParcel(`in`: Parcel): MovieDetail {
                return MovieDetail(`in`)
            }

            override fun newArray(size: Int): Array<MovieDetail?> {
                return arrayOfNulls(size)
            }
        }
    }
}