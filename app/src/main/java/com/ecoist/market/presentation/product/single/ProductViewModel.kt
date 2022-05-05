package com.ecoist.market.presentation.product.single

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ecoist.market.data.model.Photo
import com.ecoist.market.data.model.Product
import com.ecoist.market.data.roomdb.PhotoRepositoryEco
import com.ecoist.market.data.roomdb.ProductModel
import com.ecoist.market.data.roomdb.ProductRepositoryEco
import com.ecoist.market.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

/**
 *Created by Yehor Kudimov on 01.04.2021.
 */
class ProductViewModel(
    application: Application,
    private val repos: ProductRepositoryEco,
    private val reposik: PhotoRepositoryEco
) :
    BaseViewModel(application) {

    val productLiveData: LiveData<Product>
        get() = productEmitter
    val photoLiveData: LiveData<List<Photo>>
        get() = photoEmitter

    private val productEmitter = MutableLiveData<Product>()
    private val photoEmitter = MutableLiveData<List<Photo>>()

    fun product(id: Long) = repos.getProductByIdOne(id).asLiveData()

     fun  photo(id: String?) =reposik.listPhoto(id).asLiveData()
    fun checkFav(mod: ProductModel) {
        mod.updateLike()
        viewModelScope.launch {
            repos.saveModel(mod)
        }
    }
    fun buyEcoTovar(mod: ProductModel) {
        mod.updateBucket()
        viewModelScope.launch {
            repos.saveModel(mod)
        }
    }
}
