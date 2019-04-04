package com.karavatskiy.serhii.requestmovieslist.data.local

import android.arch.persistence.room.TypeConverter

/**
 * Created by Serhii on 31.03.2019.
 */
class GenresTypeConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromGenres(genres: List<String>): String {
            return genres.joinToString(",")
        }

        @TypeConverter
        @JvmStatic
        fun toGenres(data: String): List<String> {
            return data.split(",")
        }
    }
}