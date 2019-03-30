package com.karavatskiy.serhii.requestmovieslist

import android.app.Application
import com.karavatskiy.serhii.requestmovieslist.data.retrofit.MoviesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Serhii on 29.03.2019.
 */
class App : Application() {

    /*fun movieApi(): MoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MoviesApi::class.java)
    }*/
}