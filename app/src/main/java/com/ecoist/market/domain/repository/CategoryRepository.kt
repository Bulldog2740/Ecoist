package com.ecoist.market.domain.repository

import com.ecoist.market.data.mapper.CategoryMapper
import com.ecoist.market.data.model.CategoryModel
import com.ecoist.market.data.response.CategoryResponse
import com.ecoist.market.data.roomdb.DataBase
import com.ecoist.market.util.Resource
import com.ecoist.market.util.networkBoundResource
import com.ecoist.market.domain.api.ApiService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
class CategoryRepository(
    private val apiService: ApiService
) {

    companion object {
        /**
         * Id = 1 it id of top level categories on the site.
         */
        private const val TOP_LEVEL_CATEGORY_PARENT_ID: Long = 1
    }

    /**
     * Get db instance
     */
    val dao = DataBase.instance!!.getCategoryDao()

    /**
     * Get top level [CategoryResponse]'s as on the site.
     *
     * @return list with market main categories.
     */
    suspend fun getTopLevelCategories(): List<CategoryModel> {
        return this.getChildCategoriesOf(TOP_LEVEL_CATEGORY_PARENT_ID)
    }

    /**
     * Get list with child categories for destination [parentId].
     *
     * @param parentId - link to parent [CategoryResponse.id].
     * @return list of child categories of parent.
     */
    suspend fun getChildCategoriesOf(parentId: Long): List<CategoryModel> {
        val childCategories = apiService.getChildCategories(parentId)
        return CategoryMapper.mapModel(childCategories)
    }

    /**
     * Get list with all existed categories on the site.
     *
     * @return list with all market categories.
     */

    fun getItems(parentId: Long): Flow<Resource<List<CategoryModel>>> {
        return networkBoundResource(
            query = { dao.getCategoryFlow(parentId) },
            fetch = {
                apiService.getChildCategories(parentId)
            },
            saveFetchResult = { item ->
                CategoryMapper.mapModel(item).also { dao.insert(*it.toTypedArray()) }
            },
            onFetchFailed = {},
            shouldFetch = { true }
        )
    }

    val io: CoroutineDispatcher
        get() = Dispatchers.IO

}



