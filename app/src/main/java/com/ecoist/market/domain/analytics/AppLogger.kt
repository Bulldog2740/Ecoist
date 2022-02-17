package com.ecoist.market.domain.analytics

import android.util.Log

object AppLogger {

    fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun wtf(tag: String, message: String) {
        Log.wtf(tag, message)
    }

    fun error(tag: String, message: String) {
        Log.e(tag, message)
    }
}