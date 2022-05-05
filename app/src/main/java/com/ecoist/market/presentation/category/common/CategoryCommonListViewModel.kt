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

    val categoryListLiveDataRoom: LiveData<List<CategoryModel>>
        get() = categoryListEmitterRoom
    private val categoryListEmitterRoom = MutableLiveData<List<CategoryModel>>()

    fun resource(parentId:Long)=repo.getItems(parentId).asLiveData()

/*
    fun liveDate(parentCategoryId: Long) = repo.getLiveDateById(parentCategoryId)

    fun initCommonCat(parentCategoryId: Long) {
        viewModelScope.launch(io) {
            repo.initCommon(parentCategoryId)
        }
    }*/
}
