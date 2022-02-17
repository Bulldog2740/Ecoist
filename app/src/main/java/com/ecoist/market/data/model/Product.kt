package com.ecoist.market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val mpn: String?,
    val galleryName: String?,
    val rating: Int?,
    val descriptionFull: String?,
    val discount: String?,
    val garanty: String?,
    val idBrand: Int?,
    val name: String?,
    val baseId: Int?,
    val odiscount: String?,
    val mark: Int?,
    val idCategory: Int?,
    val id: Long,
    val priceAutogen: Double?,
    val coment: String?,
    val price: String?,
    val alias: String?,
    val isPublic: Int?,
    val idImage: Int?,
    val deleted: Int?,
    val description: String?,
    val imageUrl: String?,
    val urlForImages:String?
) : Parcelable