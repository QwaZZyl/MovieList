package com.karavatskiy.serhii.requestmovieslist.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription

/**
 * Created by Serhii on 31.03.2019.
 */
@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    fun insertList(movies: List<MovieDescription>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(movie: MovieDescription)

    @Query("SELECT * FROM moviedescription")
    fun getAll(): List<MovieDescription>

    @Query("SELECT * FROM moviedescription WHERE id = :id")
    fun getById(id: Int): MovieDescription

    @Query("DELETE FROM moviedescription")
    fun deleteAll()

    @Query("SELECT COUNT() FROM moviedescription")
    fun getCountItems(): Int
}