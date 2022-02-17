package com.ecoist.market.di.category

import com.ecoist.market.presentation.category.main.CategoryMainListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun categoryMainListModule() = module {
    viewModel {
        CategoryMainListViewModel(
            application = androidApplication(),
            repository = get(),
            repo = get()

        )
    }
}