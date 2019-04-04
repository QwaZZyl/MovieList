package com.karavatskiy.serhii.requestmovieslist.ui.filterslist

import com.karavatskiy.serhii.requestmovieslist.data.local.Repository
import com.karavatskiy.serhii.requestmovieslist.data.model.Error
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.ResultRequest
import com.karavatskiy.serhii.requestmovieslist.data.model.Success

/**
 * Created by Serhii on 31.03.2019.
 */
class FiltersPresenterDefault : FiltersPresenter {

    override suspend fun requestData(): ResultRequest<List<MovieDescription>> =
        try {
            val repository = Repository()
            val result = repository.getMovies()
            Success(result)
        } catch (throwable: Throwable) {
            Error(throwable)
        }
}