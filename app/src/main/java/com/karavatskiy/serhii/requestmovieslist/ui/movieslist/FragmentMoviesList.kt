package com.karavatskiy.serhii.requestmovieslist.ui.movieslist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.FiltersMovies
import com.karavatskiy.serhii.requestmovieslist.data.model.Error
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterDirector
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterGenre
import com.karavatskiy.serhii.requestmovieslist.data.model.FilterYear
import com.karavatskiy.serhii.requestmovieslist.data.model.FiltersData
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.Success
import com.karavatskiy.serhii.requestmovieslist.ui.ControlActivity
import com.karavatskiy.serhii.requestmovieslist.ui.base.BaseFragment
import com.karavatskiy.serhii.requestmovieslist.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_movies_list.clDirectors
import kotlinx.android.synthetic.main.fragment_movies_list.clGenres
import kotlinx.android.synthetic.main.fragment_movies_list.clYears
import kotlinx.android.synthetic.main.fragment_movies_list.ibDisableDirectorsFil
import kotlinx.android.synthetic.main.fragment_movies_list.ibDisableGenresFil
import kotlinx.android.synthetic.main.fragment_movies_list.ibDisableYearsFil
import kotlinx.android.synthetic.main.fragment_movies_list.rvMoviesList
import kotlinx.android.synthetic.main.fragment_movies_list.tvDirectorsFilters
import kotlinx.android.synthetic.main.fragment_movies_list.tvGenresFilters
import kotlinx.android.synthetic.main.fragment_movies_list.tvMsgNoMovies
import kotlinx.android.synthetic.main.fragment_movies_list.tvTitleFilters
import kotlinx.android.synthetic.main.fragment_movies_list.tvYearsFilters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Serhii on 28.03.2019.
 */
class FragmentMoviesList : BaseFragment<ControlActivity>() {

    private val TAG = this::class.java.simpleName
    private var rvMoviesAdapter: RVMoviesAdapter = RVMoviesAdapter()
    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private var years: ArrayList<FilterYear> = ArrayList()
    private var directors: ArrayList<FilterDirector> = ArrayList()
    private var genres: ArrayList<FilterGenre> = ArrayList()
    private var movies: List<MovieDescription> = emptyList()

    companion object Factory {
        fun newInstance(): FragmentMoviesList = FragmentMoviesList()

        fun newInstance(filtersData: FiltersData): FragmentMoviesList {
            val bundle = Bundle()
            bundle.putParcelableArrayList(
                FiltersData.KEY_DIRECTORS,
                ArrayList(filtersData.directors.getValue(filtersData.directors.keys.first()))
            )
            bundle.putParcelableArrayList(
                FiltersData.KEY_YEARS,
                ArrayList(
                    filtersData.years.getValue(filtersData.years.keys.first())
                )
            )
            bundle.putParcelableArrayList(
                FiltersData.KEY_GENRES,
                ArrayList(filtersData.genres.getValue(filtersData.genres.keys.first()))
            )
            return FragmentMoviesList().apply { arguments = bundle }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.movies_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.ibFilters) {
            activity.goToFilters()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showEnableFilters() {

        if (years.isEmpty() && genres.isEmpty() && directors.isEmpty()) {
            tvTitleFilters.visibility = View.GONE
        } else tvTitleFilters.visibility = View.VISIBLE
        if (genres.isEmpty()) {
            clGenres.visibility = View.GONE
        } else {
            clGenres.visibility = View.VISIBLE
            tvGenresFilters.text = genres.joinToString { it.criteria }
            ibDisableGenresFil.setOnClickListener {
                genres.clear()
                showEnableFilters()
                updateMoviesList()
            }
        }
        if (directors.isEmpty()) {
            clDirectors.visibility = View.GONE
        } else {
            clDirectors.visibility = View.VISIBLE
            tvDirectorsFilters.text = directors.joinToString { it.criteria }
            ibDisableDirectorsFil.setOnClickListener {
                directors.clear()
                showEnableFilters()
                updateMoviesList()
            }
        }
        if (years.isEmpty()) {
            clYears.visibility = View.GONE
        } else {
            clYears.visibility = View.VISIBLE
            tvYearsFilters.text = years.joinToString { it.criteria }
            ibDisableYearsFil.setOnClickListener {
                years.clear()
                showEnableFilters()
                updateMoviesList()
            }
        }
    }

    private fun updateMoviesList() {
        showEnableFilters()
        rvMoviesAdapter.setMovies(FiltersMovies.filteredMovies(movies, years, genres, directors))
        if (rvMoviesAdapter.itemCount == 0) {
            tvMsgNoMovies.visibility = View.VISIBLE
        }
    }

    private fun onGetDataSuccess(moviesList: List<MovieDescription>) {
        movies = moviesList
        updateMoviesList()
    }

    private fun onGetDataError(throwable: Throwable) {
        LogUtils.logDebug(TAG, throwable.message.toString())
    }

    override fun setupData() {
        setHasOptionsMenu(true)
        activity.title = getString(R.string.title_movies)
        activity.supportActionBar!!.setDisplayShowHomeEnabled(false)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        if (arguments?.getParcelableArrayList<FilterYear>(FiltersData.KEY_YEARS) != null) {
            years = arguments?.getParcelableArrayList(FiltersData.KEY_YEARS)!!
            directors = arguments?.getParcelableArrayList(FiltersData.KEY_DIRECTORS)!!
            genres = arguments?.getParcelableArrayList(FiltersData.KEY_GENRES)!!
            LogUtils.logDebug("onGetDataSuccess", directors.size.toString())
        }

        val presenter = MoviesListPresenter()
        ioScope.launch {
            val result = presenter.requestData()
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

    override fun getLayoutId(): Int = R.layout.fragment_movies_list
}