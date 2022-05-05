package com.ecoist.market.presentation.category.main

import android.app.Application
import androidx.lifecycle.asLiveData
import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.presentation.base.BaseViewModel

class CategoryMainListViewModel(
    application: Application,
    private val repository: CategoryRepository, private val repo: CategoryRepository
) : BaseViewModel(application) {

  //  val liveDate = repo.getLiveDateById(1)
    val resource=repo.getItems(1).asLiveData()

    /*fun initMain() {
        viewModelScope.launch(io) {
            repo.getTopLevelCategoriesRoom()
        }
    }*/
}