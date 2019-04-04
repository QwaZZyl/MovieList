package com.karavatskiy.serhii.requestmovieslist

import android.app.Application
import android.arch.persistence.room.Room
import com.karavatskiy.serhii.requestmovieslist.data.local.AppDatabase
import com.karavatskiy.serhii.requestmovieslist.data.local.Dao
import com.karavatskiy.serhii.requestmovieslist.data.retrofit.MoviesApi
import com.karavatskiy.serhii.requestmovieslist.utils.Const

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl(Const.MoviesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        moviesApi = retrofit.create(MoviesApi::class.java)
        val room = Room.databaseBuilder(this, AppDatabase::class.java, "database").build()
        dao = room.daoAccess()
    }

    companion object {
        lateinit var moviesApi: MoviesApi
            private set
        lateinit var dao: Dao
            private set
    }
}
