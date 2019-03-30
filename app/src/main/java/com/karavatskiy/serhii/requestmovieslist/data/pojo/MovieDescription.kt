package com.karavatskiy.serhii.requestmovieslist.data.pojo

import com.google.gson.annotations.SerializedName

data class MovieDescription(

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
