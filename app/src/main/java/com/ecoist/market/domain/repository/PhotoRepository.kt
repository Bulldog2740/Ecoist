package com.ecoist.market.domain.repository

import com.ecoist.market.data.mapper.PhotoMapper
import com.ecoist.market.data.model.PhotoModel
import com.ecoist.market.data.roomdb.DataBase
import com.ecoist.market.data.roomdb.dao.PhotoDao
import com.ecoist.market.data.roomdb.dao.ProductDao
import com.ecoist.market.util.networkBoundResource
import com.ecoist.market.domain.api.ApiService
import com.ecoist.market.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

/**
 *Created by Yehor Kudimov on 3/05/2021.
 */
class PhotoRepository(private val apiService: ApiService,
                      private val dao: PhotoDao
) {
    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    fun listPhoto(name: String?): Flow<Resource<List<PhotoModel>>> {
        return networkBoundResource(
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