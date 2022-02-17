package com.ecoist.market.presentation.category.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoist.market.data.model.Category
import com.ecoist.market.data.roomdb.CategoryModel
import com.ecoist.market.data.roomdb.CategoryRepositoryEco
import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryMainListViewModel(
    application: Application,
    private val repository: CategoryRepository, private val repo: CategoryRepositoryEco
) : BaseViewModel(application) {

  //  val liveDate = repo.getLiveDateById(1)
    val resource=repo.getItems(1).asLiveData()

    /*fun initMain() {
        viewModelScope.launch(io) {
            repo.getTopLevelCategoriesRoom()
        }
    }*/
}