
package com.ecoist.market.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Database(
    entities = [ProductModel::class, PhotoModel::class, CategoryModel::class],
    exportSchema = false,
    version = 1
)
abstract class EcoDataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getCategoryDao(): CategoryDao
    companion object {
        var instance: EcoDataBase? = null
        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context, EcoDataBase::class.java, "eco_db"
            ).build()
        }
    }
}