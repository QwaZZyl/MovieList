package com.karavatskiy.serhii.requestmovieslist.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Serhii on 28.03.2019.
 */
abstract class BaseFragment : Fragment() {

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
//todo add new line in settings