package com.karavatskiy.serhii.requestmovieslist.ui.filterslist

import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.Mapper
import com.karavatskiy.serhii.requestmovieslist.data.model.Error
import com.karavatskiy.serhii.requestmovieslist.data.model.FiltersData
import com.karavatskiy.serhii.requestmovieslist.data.model.MovieDescription
import com.karavatskiy.serhii.requestmovieslist.data.model.Success
import com.karavatskiy.serhii.requestmovieslist.ui.ControlActivity
import com.karavatskiy.serhii.requestmovieslist.ui.base.BaseFragment
import com.karavatskiy.serhii.requestmovieslist.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_select_filters.btnApply
import kotlinx.android.synthetic.main.fragment_select_filters.rvFilters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Created by Serhii on 30.03.2019.
 */
class FragmentFiltersList : BaseFragment<ControlActivity>() {

    private val TAG = this::class.java.simpleName

    private val job = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + job)
    private val uiScope = CoroutineScope(Dispatchers.Main + job)
    private val rvFiltersAdapter: RVFiltersAdapter = RVFiltersAdapter()
    private lateinit var presenter: FiltersPresenter
    private lateinit var data: FiltersData

    companion object Factory {
        fun newInstance(): FragmentFiltersList = FragmentFiltersList()
    }

    override fun getLayoutId(): Int = R.layout.fragment_select_filters

    private fun onGetDataSuccess(moviesList: List<MovieDescription>) {
        data = Mapper(moviesList).getFilters()
        rvFiltersAdapter.setFilters(data)
    }

    fun applyFilters() {
        fragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        activity.goToMoviesList(rvFiltersAdapter.getSelectedFilters(data))
    }

    private fun onGetDataError(throwable: Throwable) {
        LogUtils.logDebug(TAG, throwable.message.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.filters_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.ibClear) {
            rvFiltersAdapter.disableCheckBox()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setupData() {
        setHasOptionsMenu(true)
        retainInstance = true
        activity.title = getString(R.string.title_filters)
        activity.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar!!.setDisplayShowHomeEnabled(true)
        btnApply.setOnClickListener { applyFilters() }
        rvFilters.adapter = rvFiltersAdapter
        rvFilters.layoutManager = LinearLayoutManager(context)
        presenter = FiltersPresenterDefault()
        ioScope.launch {
            val result = presenter.requestData()
            uiScope.launch {
                when (result) {
                    is Success -> onGetDataSuccess(result.data)
                    is Error -> onGetDataError(result.exception)
                }
            }
        }
    }
}
