package com.ecoist.market.data.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Keep
data class ProductResponse(
    @SerializedName("mpn") val mpn: String?,
    @SerializedName("GalleryName") val galleryName: String?,
    @SerializedName("rating") val rating: Int?,
    @SerializedName("DescriptionFull") val descriptionFull: String?,
    @SerializedName("discount") val discount: String?,
    @SerializedName("garanty") val garanty: String?,
    @SerializedName("idBrand") val idBrand: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("baseId") val baseId: Int?,
    @SerializedName("odiscount") val odiscount: String?,
    @SerializedName("mark") val mark: Int?,
    @SerializedName("idCategory") val idCategory: Int?,
    @SerializedName("id") val id: Long,
    @SerializedName("priceAutogen") val priceAutogen: Double?,
    @SerializedName("coment") val coment: String?,
    @SerializedName("price") val price: String?,
    @SerializedName("alias") val alias: String?,
    @SerializedName("isPublic") val isPublic: Int?,
    @SerializedName("idImage") val idImage: Int?,
    @SerializedName("deleted") val deleted: Int?,
    @SerializedName("description") val description: String?
) : Parcelable
