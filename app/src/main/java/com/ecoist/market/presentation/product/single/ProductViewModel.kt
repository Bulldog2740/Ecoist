package com.ecoist.market.presentation.product.single

import android.app.Application
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoist.market.domain.repository.PhotoRepository
import com.ecoist.market.data.model.ProductModel
import com.ecoist.market.domain.repository.ProductRepository
import com.ecoist.market.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 *Created by Yehor Kudimov on 01.04.2021.
 */
class ProductViewModel(
    application: Application,
    private val productRepos: ProductRepository,
    private val photoRepository: PhotoRepository
) :
    BaseViewModel(application) {

    fun product(id: Long) = productRepos.getProductByIdOne(id).asLiveData()

    fun photo(id: String?) = photoRepository.listPhoto(id).asLiveData()
    fun checkFav(product: ProductModel) {
        product.updateLike()
        viewModelScope.launch {
            productRepos.saveModel(product)
        }
    }

    fun buyProduct(product: ProductModel) {
        product.updateBucket()
        viewModelScope.launch {
            productRepos.saveModel(product)
        }
    }
}
