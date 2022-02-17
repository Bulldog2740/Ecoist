package com.ecoist.market.di.category

import com.ecoist.market.presentation.category.common.CategoryCommonListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


fun categoryCommonListModule() = module {
    viewModel {
        CategoryCommonListViewModel(
            application = androidApplication(),
            repo = get()
        )
    }
}
