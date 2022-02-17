package com.ecoist.market.domain.api

import androidx.annotation.Keep
import com.ecoist.market.data.response.CategoryResponse
import com.ecoist.market.data.response.PhotoResponse
import com.ecoist.market.data.response.ProductResponse
import com.ecoist.market.data.roomdb.CategoryModel
import com.ecoist.market.data.roomdb.PhotoModel
import com.ecoist.market.data.roomdb.ProductModel
import com.ecoist.market.data.roomdb.apiForTelegram
import kotlinx.coroutines.Deferred
import retrofit2.http.*


@Keep
interface ApiService {

    @GET("tss/category?a=list")
    suspend fun getAllCategories(): List<CategoryResponse>

    @GET("tss/category?a=list")
    fun getAllCategoriesDeffAsync(): Deferred<List<CategoryResponse>>

    @GET("tss/category?a=list_where&col=idParent")
    suspend fun getChildCategories(@Query("idParent") idParent: Long): List<CategoryResponse>

    @GET("tss/salemod?a=list")
    suspend fun getAllProducts(): List<ProductResponse>


    @GET("tss/salemod?a=list_where&col=idCategory")
    suspend fun getProductByIdOfCategory(@Query("idCategory") idCategory: Long): List<ProductResponse>


    @GET("tss/salemod?a=load&col=id")
    suspend fun getProductById(@Query("id") id: Long): ProductResponse


    @GET("tss/gallery?a=list_where&col=name")
    suspend fun getPhotoList(@Query("name") name: String?): List<PhotoResponse>




}