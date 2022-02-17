package com.ecoist.market.data.mapper

import androidx.room.ColumnInfo
import com.ecoist.market.data.model.Photo
import com.ecoist.market.data.model.Product
import com.ecoist.market.data.response.PhotoResponse
import com.ecoist.market.data.response.ProductResponse
import com.ecoist.market.data.roomdb.ProductModel


object ProductMapper {


    fun mapRoom(products: List<ProductResponse>): List<ProductModel> {
        return products
            .filter {
                // Exclude non-active products
                it.isPublic == 1
            }
            .map { productResponse -> mapSingleModel(productResponse) }
    }


    fun mapSingleModel(productResponse: ProductResponse): ProductModel {

        val imageUrl: String? = buildString {
            append("https://ecoist.com.ua/gallery/")
            append(productResponse.galleryName)
            append("/image_")
            append(productResponse.idImage)
            append("_120_120.jpg")
        }

        val urlForImages: String? = buildString {
            append(productResponse.galleryName)
        }

        return ProductModel(
            mpn = productResponse.mpn,
            galleryName = productResponse.galleryName,
            rating = productResponse.rating,
            descriptionFull = productResponse.descriptionFull,
            discount = productResponse.discount,
            garanty = productResponse.garanty,
            idBrand = productResponse.idBrand,
            name = productResponse.name,
            baseId = productResponse.baseId,
            odiscount = productResponse.odiscount,
            mark = productResponse.mark,
            idCategory = productResponse.idCategory,
            id = productResponse.id,
            priceAutogen = productResponse.priceAutogen,
            coment = productResponse.coment,
            price = productResponse.price,
            alias = productResponse.alias,
            isPublic = productResponse.isPublic,
            idImage = productResponse.idImage,
            deleted = productResponse.deleted,
            description = productResponse.description,
            imageUrl =imageUrl,
            urlForImages =urlForImages
        )
    }
}