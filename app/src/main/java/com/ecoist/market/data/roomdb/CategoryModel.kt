package com.ecoist.market.data.roomdb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Entity(tableName = "category")
@Parcelize
data class CategoryModel(
    @PrimaryKey
    val id: Long,
    @ColumnInfo(name = "idParent")
    val idParent: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "alias")
    val alias: String,
    @ColumnInfo(name = "compared")
    val compared: Int,
    @ColumnInfo(name = "deleted")
    val deleted: Int,
    @ColumnInfo(name = "showBanner")
    val showBanner: Int,
    @ColumnInfo(name = "categoryOrder")
    val categoryOrder: Int,
    @ColumnInfo(name = "product_list_cols")
    val product_list_cols: Int,
    @ColumnInfo(name = "short_desc_on_product")
    val short_desc_on_product: Int,
    @ColumnInfo(name = "show_mods")
    val show_mods: Int,
    @ColumnInfo(name = "isPublic")
    val isPublic: Int,
    @ColumnInfo(name = "show_links")
    val show_links: Int,
    @ColumnInfo(name = "image")
    val image: String
) : Parcelable
