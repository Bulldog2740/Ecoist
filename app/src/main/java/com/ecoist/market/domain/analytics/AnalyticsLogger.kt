package com.ecoist.market.domain.analytics


object AnalyticsLogger {

    fun logException(throwable: Throwable) {
        throwable.printStackTrace()
    }
}