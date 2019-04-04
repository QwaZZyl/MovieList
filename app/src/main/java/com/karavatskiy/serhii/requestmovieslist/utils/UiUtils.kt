package com.karavatskiy.serhii.requestmovieslist.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast

/**
 * Created by Serhii on 29.03.2019.
 */
object UiUtils {

    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

    fun replaceFragment(fragmentManager: FragmentManager, container: Int, fragment: Fragment, tag: String) {
        fragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(container, fragment, tag).addToBackStack(tag)
            .commit()
    }

    fun replaceFragmentWithOutBackStack(
        fragmentManager: FragmentManager,
        container: Int,
        fragment: Fragment,
        tag: String
    ) {
        fragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(container, fragment, tag).addToBackStack(tag)
            .commit()
    }
}