package com.karavatskiy.serhii.requestmovieslist.utils

import android.util.Log
import com.karavatskiy.serhii.requestmovieslist.BuildConfig

/**
 * Created by Serhii on 30.03.2019.
 */
object LogUtils {

    fun logDebug(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, "logDebug:$message")
        }
    }
}