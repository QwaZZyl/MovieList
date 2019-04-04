package com.karavatskiy.serhii.requestmovieslist.data.model

import java.util.SortedSet

/**
 * Created by Serhii on 31.03.2019.
 */
data class FiltersData(
    val genres: Map<Title, SortedSet<FilterGenre>>,
    val years: Map<Title, SortedSet<FilterYear>>,
    val directors: Map<Title, SortedSet<FilterDirector>>
) {

    companion object {
        const val KEY_GENRES = "KEY_GENRES"
        const val KEY_YEARS = "KEY_YEARS"
        const val KEY_DIRECTORS = "KEY_DIRECTORS"
    }
}

