package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import com.karavatskiy.serhii.requestmovieslist.data.model.Error
import com.karavatskiy.serhii.requestmovieslist.data.model.ResultRequest
import com.karavatskiy.serhii.requestmovieslist.data.model.Success
import com.karavatskiy.serhii.requestmovieslist.data.pojo.Movies
import com.karavatskiy.serhii.requestmovieslist.data.retrofit.MoviesApi
import com.karavatskiy.serhii.requestmovieslist.utils.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Serhii on 29.03.2019.
 */
class MoviesListPresenter {

    suspend fun requsetData(): ResultRequest<Movies> =
        try {
            val result = getMovies()
            Success(result)
        } catch (throwable: Throwable) {
            Error(throwable)
        }

    private suspend fun getMovies() =
        suspendCoroutine<Movies> { continuation ->
            movieApi().getMovies().enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    val movies = response.body()
                    if (movies != null) {
                        continuation.resumeWith(Result.success(movies))
                    } else {
                        continuation.resumeWith(Result.failure(IllegalStateException("Invalid data in response")))
                    }
                }

                override fun onFailure(call: Call<Movies>, throwable: Throwable) {
                    continuation.resumeWith(Result.failure(throwable))
                }
            })
        }

    fun movieApi(): MoviesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.MoviesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(MoviesApi::class.java)
    }
}