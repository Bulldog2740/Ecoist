package com.ecoist.market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 17.01.2021.
 */
@Parcelize
data class Photo(
    val id: Long?,
    val name: String?,
    val gOrder: Int?,
    val height: Int?,
    val width: Int?,
    val imageUrl: String?
) : Parcelable
