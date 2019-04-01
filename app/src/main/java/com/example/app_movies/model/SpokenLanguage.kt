package com.example.app_movies.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SpokenLanguage : Parcelable {
    @SerializedName("iso_639_1")
    @Expose
    var iso6391: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    protected constructor(`in`: Parcel) {
        this.iso6391 = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(iso6391)
        dest.writeValue(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<SpokenLanguage> = object : Creator<SpokenLanguage> {
            override fun createFromParcel(`in`: Parcel): SpokenLanguage {
                return SpokenLanguage(`in`)
            }

            override fun newArray(size: Int): Array<SpokenLanguage?> {
                return arrayOfNulls(size)
            }
        }
    }
}