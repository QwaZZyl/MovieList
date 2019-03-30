package com.karavatskiy.serhii.requestmovieslist.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.karavatskiy.serhii.requestmovieslist.R
import com.karavatskiy.serhii.requestmovieslist.ui.movieslist.FragmentMoviesList
import com.karavatskiy.serhii.requestmovieslist.utils.UiUtils
import kotlinx.android.synthetic.main.activity_control.fragmentContainer
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ControlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        UiUtils.replaceFragment(
            supportFragmentManager,
            fragmentContainer.id,
            FragmentMoviesList.newInstance(),
            FragmentMoviesList::class.toString()
        )
    }
}
