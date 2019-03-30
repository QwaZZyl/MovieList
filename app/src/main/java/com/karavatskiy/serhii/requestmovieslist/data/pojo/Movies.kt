package com.karavatskiy.serhii.requestmovieslist.data.pojo

import com.google.gson.annotations.SerializedName

data class Movies (

    @SerializedName("values")
    val values: List<MovieDescription>
)
