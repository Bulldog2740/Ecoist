
package com.ecoist.market.data.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ecoist.market.data.model.CategoryModel
import com.ecoist.market.data.roomdb.dao.CategoryDao
import com.ecoist.market.data.roomdb.dao.PhotoDao
import com.ecoist.market.data.roomdb.dao.ProductDao

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Database(
    entities = [ProductModel::class, PhotoModel::class, CategoryModel::class],
    exportSchema = false,
    version = 1
)
abstract class DataBase : RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getCategoryDao(): CategoryDao
    companion object {
        var instance: DataBase? = null
        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context, DataBase::class.java, "eco_db"
            ).build()
        }
    }
}