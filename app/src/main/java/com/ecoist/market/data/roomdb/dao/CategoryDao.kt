package com.ecoist.market.data.roomdb.dao


import androidx.lifecycle.LiveData
import androidx.room.*
import com.ecoist.market.data.model.CategoryModel
import kotlinx.coroutines.flow.Flow

/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Dao
interface CategoryDao {

    @Query("SELECT * FROM category WHERE idParent= :id")
    fun getLiveDataCategory(id: Long): LiveData<List<CategoryModel>>

    @Query("SELECT * FROM category WHERE idParent= :id")
    fun getCategoryById(id: Long): List<CategoryModel>

    @Query("SELECT * FROM category")
    fun getCategory(): List<CategoryModel>

    @Query("SELECT * FROM category WHERE idParent= :id")
    fun getCategoryFlow(id: Long): Flow<List<CategoryModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg category: CategoryModel)

    @Delete
    suspend fun delete(vararg category: CategoryModel)

}
