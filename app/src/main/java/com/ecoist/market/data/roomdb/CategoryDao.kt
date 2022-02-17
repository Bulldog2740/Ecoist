package com.ecoist.market.data.roomdb


import androidx.lifecycle.LiveData
import androidx.room.*
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
    fun getCategoryFlowX(id:Long): Flow<List<CategoryModel>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(vararg category: CategoryModel)

    @Delete
   suspend fun delete(vararg category: CategoryModel)

}
