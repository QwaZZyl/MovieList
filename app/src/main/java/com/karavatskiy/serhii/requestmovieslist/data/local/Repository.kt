package com.karavatskiy.serhii.requestmovieslist.data.local

import com.karavatskiy.serhii.requestmovieslist.App
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.Movies
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Serhii on 31.03.2019.
 */
class Repository {

    private fun writeToDb(movies: List<MovieDescription>) {
        App.dao.insertList(movies)
    }

    private fun readFromDb(): List<MovieDescription> = App.dao.getAll()

    private suspend fun remoteMoviesRequest(): List<MovieDescription> {
        return suspendCoroutine { continuation ->
            App.moviesApi.getMovies().enqueue(object : Callback<Movies> {
                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    val movies = response.body()
                    if (movies != null) {
                        continuation.resumeWith(Result.success(movies.values))
                        GlobalScope.launch { writeToDb(movies.values) }
                    } else {
                        continuation.resumeWith(Result.failure(IllegalStateException("Invalid data in response")))
                    }
                }

                override fun onFailure(call: Call<Movies>, throwable: Throwable) {
                    continuation.resumeWith(Result.failure(throwable))
                }
            })
        }
    }

    suspend fun getMovies(): List<MovieDescription> {
        return if (isClearDB()) {
            remoteMoviesRequest()
        } else {
            readFromDb()
        }
    }

    private fun isClearDB(): Boolean {
        return App.dao.getCountItems() == 0
    }
}