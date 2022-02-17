package com.ecoist.market

import android.app.Application
import com.ecoist.market.di.Koin


class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        // Init DI
        Koin.start(this)
    }
}
