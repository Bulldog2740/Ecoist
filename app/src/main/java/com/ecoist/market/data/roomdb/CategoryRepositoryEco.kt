package com.ecoist.market.data.roomdb

import com.ecoist.market.data.mapper.CategoryMapper
import com.ecoist.market.data.response.CategoryResponse
import com.ecoist.market.domain.api.ApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
class CategoryRepositoryEco(
    private val apiService: ApiService
) {
    companion object {
        private const val TOP_LEVEL_CATEGORY_PARENT_ID: Long = 1
    }
    val dao = EcoDataBase.instance!!.getCategoryDao()

    suspend fun getChildCategoriesOfRoom(parentId: Long): List<CategoryModel> {
        val childCategories = apiService.getChildCategories(parentId)
        return CategoryMapper.mapModel(childCategories)
    }

    fun getItems(parentId: Long): Flow<Resource<List<CategoryModel>>> {
        return networkBoundResourceMay(
            query = { dao.getCategoryFlowX(parentId) },
            fetch = {
                apiService.getChildCategories(parentId)
            },
            saveFetchResult = { item ->
                CategoryMapper.mapModel(item).also { dao.insert(*it.toTypedArray()) }
            }
        )
    }

    val io: CoroutineDispatcher
        get() = Dispatchers.IO

}



