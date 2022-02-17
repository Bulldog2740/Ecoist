package com.ecoist.market.data.response

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Keep
@Entity
data class CategoryResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("idParent") val idParent: Long,
    @SerializedName("name") val name: String,
    @SerializedName("alias") val alias: String,
    @SerializedName("compared") val compared: Int,
    @SerializedName("deleted") val deleted: Int,
    @SerializedName("showBanner") val showBanner: Int,
    @SerializedName("categoryOrder") val categoryOrder: Int,
    @SerializedName("product_list_cols") val product_list_cols: Int,
    @SerializedName("short_desc_on_product") val short_desc_on_product: Int,
    @SerializedName("show_mods") val show_mods: Int,
    @SerializedName("isPublic") val isPublic: Int,
    @SerializedName("show_links") val show_links: Int
) : Parcelable