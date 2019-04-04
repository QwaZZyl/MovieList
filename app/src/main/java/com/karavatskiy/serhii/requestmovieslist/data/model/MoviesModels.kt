package com.karavatskiy.serhii.requestmovieslist.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDescription(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("desription")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("director")
    val director: String,

    @SerializedName("genre")
    val genre: List<String>,

    @SerializedName("title")
    val title: String
)

data class Movies(

    @SerializedName("values")
    val values: List<MovieDescription>
)
