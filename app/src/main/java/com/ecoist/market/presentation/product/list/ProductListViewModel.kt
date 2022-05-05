package com.ecoist.market.presentation.product.list

import android.app.Application
import androidx.lifecycle.*
import com.ecoist.market.data.model.ProductModel
import com.ecoist.market.domain.repository.ProductRepository
import com.ecoist.market.domain.repository.TelegramRepository
import com.ecoist.market.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductListViewModel(
    application: Application,
    private val repo: ProductRepository,
    private val reposiotryTelegram: TelegramRepository

) : BaseViewModel(application) {


    suspend fun sendMessageTelega(message: String) {
        reposiotryTelegram.telegramSend(message)
    }

    fun listProducts(idParent: Long) = repo.getProductById(idParent).asLiveData()

    fun favoriteLatestNews() =
        repo.getProducts().map { news -> news.filter { it.favorites } }

    fun bucket() =
        repo.getProducts().map { news -> news.filter { it.bucket } }

    fun product(id: Long) = repo.getProductByIdOne(id)

    fun checkFav(mod: ProductModel) {
        mod.updateLike()
        viewModelScope.launch {
            repo.saveModel(mod)
        }
    }

    fun buyProduct(mod: ProductModel) {
        mod.updateBucket()
        viewModelScope.launch {
            repo.saveModel(mod)
        }
    }

}
