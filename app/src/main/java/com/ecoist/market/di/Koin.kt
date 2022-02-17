package com.ecoist.market.di

import androidx.annotation.Keep
import com.ecoist.market.AppDelegate
import com.ecoist.market.di.category.categoryCommonListModule
import com.ecoist.market.di.category.categoryMainListModule
import com.ecoist.market.di.network.networkModule
import com.ecoist.market.di.product.productListModule
import com.ecoist.market.di.product.productModule
import com.ecoist.market.di.repository.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.KoinContextHandler
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

@Keep
object Koin {

    fun start(appDelegate: AppDelegate) {
        val koinApplication = GlobalContext.getOrNull()

        if (koinApplication != null) {
            // We already started KoinApplication
            return
        }

        val modules = listOf(
            networkModule(),
            repositoryModule(),
            categoryMainListModule(),
            categoryCommonListModule(),
            productListModule(),
            productModule()
        )

        startKoin {
            androidLogger(Level.NONE)
            androidContext(appDelegate)
            modules(modules)
        }
    }

    fun stop() {
        stopKoin()
    }
}