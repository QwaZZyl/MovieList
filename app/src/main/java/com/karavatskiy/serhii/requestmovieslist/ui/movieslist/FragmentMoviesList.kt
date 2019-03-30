package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import android.support.v7.widget.LinearLayoutManager
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.model.Error
import com.karavatskiy.serhii.requestmovieslist.data.model.Success
import com.karavatskiy.serhii.requestmovieslist.data.pojo.Movies
import com.karavatskiy.serhii.requestmovieslist.ui.base.BaseFragment
import com.karavatskiy.serhii.requestmovieslist.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_movies_list.rvMoviesList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Serhii on 28.03.2019.
 */
class FragmentMoviesList : BaseFragment() {

    private val TAG = this::class.java.simpleName
    private var rvMoviesAdapter: RVMoviesAdapter = RVMoviesAdapter()
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    companion object Factory {
        fun newInstance(): FragmentMoviesList = FragmentMoviesList()
    }

    fun onGetDataSuccess(moviesList: Movies) {
        rvMoviesAdapter.setMovies(moviesList)
    }

    fun onGetDataError(throwable: Throwable) {
        LogUtils.logDebug(TAG, throwable.message.toString())
    }

    override fun setupData() {
        val presenter = MoviesListPresenter()
        ioScope.launch {
            val result = presenter.requsetData()
            uiScope.launch {
                when (result) {
                    is Success -> onGetDataSuccess(result.data)
                    is Error -> onGetDataError(result.exception)
                }
            }
        }
        rvMoviesList.adapter = rvMoviesAdapter
        rvMoviesList.layoutManager = LinearLayoutManager(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_movies_list
    }
}