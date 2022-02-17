package com.ecoist.market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
     val id: Long,
     val idParent: Long,
     val name: String,
     val alias: String,
     val compared: Int,
     val deleted: Int,
     val showBanner: Int,
     val categoryOrder: Int,
     val product_list_cols: Int,
     val short_desc_on_product: Int,
     val show_mods: Int,
     val isPublic: Int,
     val show_links: Int,
     val image:String
) : Parcelable