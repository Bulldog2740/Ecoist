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
    private val repos: ProductRepository,
    private val reposik: PhotoRepository
) :
    BaseViewModel(application) {

    fun product(id: Long) = repos.getProductByIdOne(id).asLiveData()

    fun photo(id: String?) = reposik.listPhoto(id).asLiveData()
    fun checkFav(product: ProductModel) {
        product.updateLike()
        viewModelScope.launch {
            repos.saveModel(product)
        }
    }

    fun buyProduct(product: ProductModel) {
        product.updateBucket()
        viewModelScope.launch {
            repos.saveModel(product)
        }
    }
}
