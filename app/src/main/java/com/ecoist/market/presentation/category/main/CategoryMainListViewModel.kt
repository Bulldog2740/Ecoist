package com.ecoist.market.presentation.category.main

import android.app.Application
import androidx.lifecycle.asLiveData
import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.presentation.base.BaseViewModel

class CategoryMainListViewModel(
    application: Application,
    repository: CategoryRepository
) : BaseViewModel(application) {

    val resource = repository.getItems(1).asLiveData()
}