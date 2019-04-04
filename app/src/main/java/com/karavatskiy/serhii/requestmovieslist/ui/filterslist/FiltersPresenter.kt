package com.karavatskiy.serhii.requestmovieslist.ui.filterslist

import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.ResultRequest

/**
 * Created by Serhii on 31.03.2019.
 */
interface FiltersPresenter {

    suspend fun requestData(): ResultRequest<List<MovieDescription>>
}