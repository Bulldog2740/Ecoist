package com.ecoist.market.data.roomdb

import com.ecoist.market.data.mapper.PhotoMapper
import com.ecoist.market.data.mapper.ProductMapper
import com.ecoist.market.domain.api.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 *Created by Yehor Kudimov on 3/05/2021.
 */
class PhotoRepositoryEco(private val apiService: ApiService) {
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    private val dao = EcoDataBase.instance!!.getPhotoDao()
    fun listPhoto(name: String?): Flow<Resource<List<PhotoModel>>> {
        return networkBoundResourceMay(
            query = { dao.flowPhoto(name) },
            fetch = {
                apiService.getPhotoList(name)
            },
            saveFetchResult = { item ->
                withContext(io) {
                    PhotoMapper.mapToPhotoModel(item).also { dao.insert(*it.toTypedArray()) }
                }
            }
        )
    }

}