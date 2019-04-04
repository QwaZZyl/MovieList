package com.karavatskiy.serhii.requestmovieslist.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.data.model.FiltersData
import com.karavatskiy.serhii.requestmovieslist.ui.filterslist.FragmentFiltersList
import com.karavatskiy.serhii.requestmovieslist.ui.movieslist.FragmentMoviesList
import com.karavatskiy.serhii.requestmovieslist.utils.UiUtils
import kotlinx.android.synthetic.main.activity_control.fragmentContainer
import kotlinx.android.synthetic.main.activity_control.tbMain

class ControlActivity : AppCompatActivity() {

    private var doubleClicked: Boolean = false
    private lateinit var moviesList: FragmentMoviesList
    private lateinit var filtersList: FragmentFiltersList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            filtersList = supportFragmentManager.getFragment(savedInstanceState, "tag") as FragmentFiltersList
        }
        setContentView(R.layout.activity_control)
        setSupportActionBar(tbMain)
        goToMoviesList()
    }

    fun goToMoviesList(filters: FiltersData? = null) {
        if (filters != null) {
            moviesList = FragmentMoviesList.newInstance(filters)
            UiUtils.replaceFragment(
                supportFragmentManager,
                fragmentContainer.id,
                moviesList,
                moviesList::class.java.simpleName
            )
        } else {
            moviesList = FragmentMoviesList.newInstance()
            UiUtils.replaceFragment(
                supportFragmentManager,
                fragmentContainer.id,
                moviesList,
                moviesList::class.java.simpleName
            )
        }
    }

    fun goToFilters() {
        filtersList = FragmentFiltersList.newInstance()
        UiUtils.replaceFragmentWithOutBackStack(
            supportFragmentManager,
            fragmentContainer.id,
            filtersList,
            filtersList::class.java.simpleName
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            if (doubleClicked) super.finish()
            doubleClicked = true
            UiUtils.toast(this, getString(R.string.message_double_click_exit))
            Handler().postDelayed({ doubleClicked = false }, 2000)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
