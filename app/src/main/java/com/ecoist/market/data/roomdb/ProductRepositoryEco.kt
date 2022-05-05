package com.ecoist.market.data.roomdb

import com.ecoist.market.data.mapper.ProductMapper
import com.ecoist.market.domain.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
class ProductRepositoryEco(private val apiService: ApiService) {

    val io: CoroutineDispatcher
        get() = Dispatchers.IO
    val main: CoroutineDispatcher
        get() = Dispatchers.Main

    private val dao = DataBase.instance!!.getProductDao()

    fun getProductById(parentId: Long): Flow<Resource<List<ProductModel>>> {
        return networkBoundResource(
            query = { dao.findByIdFlowx(parentId) },
            fetch = {
                apiService.getProductByIdOfCategory(parentId)
            },
            saveFetchResult = { item ->
                ProductMapper.mapRoom(item).also { dao.insert(*it.toTypedArray()) }
            }
        )
    }
    fun getProductByIdOne(id: Long): Flow<Resource<ProductModel>> {
        return networkBoundResource(
            query = { dao.findByIdFlowxOne(id) },
            fetch = {
                apiService.getProductById(id)
            },
            saveFetchResult = { item ->
                ProductMapper.mapSingleModel(item).also { dao.insert(it) }
            }
        )
    }
    fun getProducts():Flow<List<ProductModel>>{
        return dao.findAllFlow()
    }

    suspend fun saveModel(model: ProductModel) = withContext(io) {
        dao.update(model)
    }
}