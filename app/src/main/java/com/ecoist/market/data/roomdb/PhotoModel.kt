package com.ecoist.market.data.roomdb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 *Created by Yehor Kudimov on 3/05/2021.
 */
@Entity(tableName = "photo")
@Parcelize
data class PhotoModel(
    @PrimaryKey val id: Long?,
    val name: String?,
    val gOrder: Int?,
    val height: Int?,
    val width: Int?,
    val imageUrl: String?

) : Parcelable