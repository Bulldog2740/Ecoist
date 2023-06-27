package com.ecoist.market.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

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