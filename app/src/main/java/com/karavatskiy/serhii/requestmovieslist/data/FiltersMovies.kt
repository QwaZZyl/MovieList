package com.karavatskiy.serhii.requestmovieslist.data

import com.karavatskiy.serhii.requestmovieslist.data.model.FilterDirector
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterGenre
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterYear
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription

/**
 * Created by Serhii on 02.04.2019.
 */
object FiltersMovies {

    fun filteredMovies(
        movies: List<MovieDescription>,
        years: List<FilterYear>,
        genres: List<FilterGenre>,
        directors: List<FilterDirector>
    ): List<MovieDescription> {
        if (years.isEmpty() && genres.isEmpty() && directors.isEmpty()) {
            return movies
        }
        var moviesFiltered: List<MovieDescription>
        moviesFiltered = if (genres.isNotEmpty()) {
            with(genres.map { it.criteria }) {
                movies.filter {
                    it.genre.containsAll(this)
                }
            }
        } else movies

        moviesFiltered = if (years.isNotEmpty()) {
            with(years.map { it.criteria }) {
                moviesFiltered.filter {
                    this.contains(it.year.toString())
                }
            }
        } else moviesFiltered

        moviesFiltered = if (directors.isNotEmpty()) {
            with(directors.map { it.criteria }) {
                moviesFiltered.filter {
                    this.contains(it.director)
                }
            }
        } else moviesFiltered
        return moviesFiltered
    }
}