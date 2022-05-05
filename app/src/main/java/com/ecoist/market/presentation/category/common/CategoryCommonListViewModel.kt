package com.ecoist.market.presentation.category.common

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.ecoist.market.data.model.CategoryModel
import com.ecoist.market.domain.repository.CategoryRepository
import com.ecoist.market.presentation.base.BaseViewModel


class CategoryCommonListViewModel(
    application: Application,
    private val repo: CategoryRepository
) : BaseViewModel(application) {
    fun resource(parentId:Long)=repo.getItems(parentId).asLiveData()
}
