package com.ecoist.market.data.roomdb

import android.os.BaseBundle
import android.os.Parcelable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Entity(tableName = "productEco")
@Parcelize
data class ProductModel(
    @ColumnInfo(name = "mpn") val mpn: String?,
    @ColumnInfo(name = "GalleryName") val galleryName: String?,
    @ColumnInfo(name = "rating") val rating: Int?,
    @ColumnInfo(name = "DescriptionFull") val descriptionFull: String?,
    @ColumnInfo(name = "discount") val discount: String?,
    @ColumnInfo(name = "garanty") val garanty: String?,
    @ColumnInfo(name = "idBrand") val idBrand: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "baseId") val baseId: Int?,
    @ColumnInfo(name = "odiscount") val odiscount: String?,
    @ColumnInfo(name = "mark") val mark: Int?,
    @ColumnInfo(name = "idCategory") val idCategory: Int?,
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "priceAutogen") val priceAutogen: Double?,
    @ColumnInfo(name = "coment") val coment: String?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "alias") val alias: String?,
    @ColumnInfo(name = "isPublic") val isPublic: Int?,
    @ColumnInfo(name = "idImage") val idImage: Int?,
    @ColumnInfo(name = "deleted") val deleted: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "imageUrl")val imageUrl: String?,
    @ColumnInfo(name = "urlForImages") val urlForImages:String?,
    @get:Bindable
    var favorites: Boolean = false,
    @get:Bindable
    var bucket: Boolean = false
):Parcelable,BaseObservable(){
    fun updateLike(){
        favorites=!favorites
        notifyChange()
    }
    fun updateBucket(){
        bucket=!bucket
        notifyChange()
    }
}