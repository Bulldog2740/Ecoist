package com.ecoist.market.data.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow


/**
 *Created by Yehor Kudimov on 3/05/2021.
 */
@Dao
interface PhotoDao {
    @Query("SELECT * FROM photo WHERE name = :name")
    fun flowPhoto(name: String?): Flow<List<PhotoModel>>

    @Query("SELECT * FROM photo WHERE name = :name")
    fun photoFlow(name: String): Flow<PhotoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photo: PhotoModel)

    @Delete
    fun delete(vararg photo: PhotoModel)

    @Update
    fun update(vararg photo: PhotoModel)
}