package com.karavatskiy.serhii.requestmovieslist.data.retrofit

import com.karavatskiy.serhii.requestmovieslist.data.pojo.Movies
import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {

    @GET("movies")
    fun getMovies(): Call<Movies>
}
