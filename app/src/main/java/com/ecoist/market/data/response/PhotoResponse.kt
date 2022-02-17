package com.ecoist.market.data.response

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 17.01.2021.
 */
@Parcelize
@Keep
data class PhotoResponse(
    @SerializedName("id") val id: Long?,
    @SerializedName("name") val name: String?,
    @SerializedName("gOrder") val gOrder: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?
    ) : Parcelable
