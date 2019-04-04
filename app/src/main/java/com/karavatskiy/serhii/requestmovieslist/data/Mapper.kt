package com.karavatskiy.serhii.requestmovieslist.data

import com.karavatskiy.serhii.requestmovieslist.data.model.FilterDirector
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterGenre
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterYear
import com.karavatskiy.serhii.requestmovieslist.data.model.FiltersData
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.Title
import java.util.SortedSet

/**
 * Created by Serhii on 31.03.2019.
 */
class Mapper(private val movies: List<MovieDescription>) {

    fun getFilters(): FiltersData {
        val genreSet: SortedSet<FilterGenre> = sortedSetOf()
        val yearSet: SortedSet<FilterYear> = sortedSetOf()
        val directorSet: SortedSet<FilterDirector> = sortedSetOf()
        for (movie in movies) {
            genreSet.addAll(movie.genre.map { FilterGenre(it) })
            yearSet.add(FilterYear(movie.year.toString()))
            directorSet.add(FilterDirector(movie.director))
        }
        return FiltersData(
            mapOf(Title("Genres") to genreSet),
            mapOf(Title("Years") to yearSet),
            mapOf(Title("Directors") to directorSet)
        )
    }
}