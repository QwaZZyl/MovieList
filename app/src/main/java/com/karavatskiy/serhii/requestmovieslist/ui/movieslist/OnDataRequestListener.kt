package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import com.karavatskiy.serhii.requestmovieslist.data.pojo.Movies

/**
 * Created by Serhii on 29.03.2019.
 */
interface OnDataRequestListener {

    fun getData(moviesList: Movies?)
}