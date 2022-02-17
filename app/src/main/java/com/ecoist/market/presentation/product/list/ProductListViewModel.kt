package com.ecoist.market.presentation.product.list

import android.app.Application
import androidx.lifecycle.*
import com.ecoist.market.data.model.Product
import com.ecoist.market.data.roomdb.ProductModel
import com.ecoist.market.data.roomdb.ProductRepositoryEco
import com.ecoist.market.domain.repository.TelegramRepository
import com.ecoist.market.presentation.base.BaseViewModel
import com.ecoist.market.presentation.product.Bucket
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductListViewModel(
    application: Application,
    private val repo: ProductRepositoryEco,
    private val reposiotryTelegram: TelegramRepository

) : BaseViewModel(application) {

    val productListLiveData: LiveData<List<Product>>
        get() = productListEmitter

    private val productListEmitter = MutableLiveData<List<Product>>()

    suspend fun sendMessageTelega(message: String) {
        reposiotryTelegram.telegramSend(message)
    }

    val productLitlLiveData: LiveData<List<Bucket.LilProduct>>
        get() = productLitlEmitter

     val productLitlEmitter = MutableLiveData<List<Bucket.LilProduct>>()

    fun listProducts(idParent: Long) = repo.getProductByIdFlowx(idParent).asLiveData()

    fun  favoriteLatestNews() =
        repo.getProductsFlow().map { news -> news.filter { it.favorites } }
    fun  bucket() =
        repo.getProductsFlow().map { news -> news.filter { it.bucket } }

    fun product(id: Long) = repo.getProductByIdFlowxSingle(id)

    fun checkFav(mod: ProductModel) {
        mod.updateLike()
        viewModelScope.launch {
                repo.saveModel(mod)
        }
    }
    fun buyEcoTovar(mod: ProductModel) {
        mod.updateBucket()
        viewModelScope.launch {
                repo.saveModel(mod)
        }
    }

}
