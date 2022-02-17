package com.ecoist.market.di.product

import com.ecoist.market.presentation.product.list.ProductListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun productListModule() = module {
    viewModel {
        ProductListViewModel(
            application = androidApplication(),
            repo = get(),
            reposiotryTelegram = get()
        )
    }
}

