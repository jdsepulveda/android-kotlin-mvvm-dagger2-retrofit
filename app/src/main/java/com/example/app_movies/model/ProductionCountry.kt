package com.example.app_movies.model

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountry : Parcelable {
    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

    protected constructor(`in`: Parcel) {
        this.iso31661 = `in`.readValue(String::class.java.classLoader) as String
        this.name = `in`.readValue(String::class.java.classLoader) as String
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(iso31661)
        dest.writeValue(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ProductionCountry> = object : Creator<ProductionCountry> {
            override fun createFromParcel(`in`: Parcel): ProductionCountry {
                return ProductionCountry(`in`)
            }

            override fun newArray(size: Int): Array<ProductionCountry?> {
                return arrayOfNulls(size)
            }
        }
    }
}