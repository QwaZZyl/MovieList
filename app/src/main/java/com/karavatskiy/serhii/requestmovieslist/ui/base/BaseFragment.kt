package com.karavatskiy.serhii.requestmovieslist.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Serhii on 28.03.2019.
 */
abstract class BaseFragment<A : AppCompatActivity> : Fragment() {

    protected lateinit var activity: A

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        this.activity = context as A
    }

    protected abstract fun getLayoutId(): Int

    protected abstract fun setupData()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }
}
