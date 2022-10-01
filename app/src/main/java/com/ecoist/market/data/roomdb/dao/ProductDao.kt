package com.ecoist.market.data.roomdb.dao

import androidx.room.*
import com.ecoist.market.data.model.ProductModel
import kotlinx.coroutines.flow.Flow


/**
 *Created by Yehor Kudimov on 3/12/2021.
 */
@Dao
interface ProductDao {

    @Query("SELECT * FROM productEco WHERE idCategory = :id")
    fun findByCategoryId(id: Long): Flow<List<ProductModel>>

    @Query("SELECT * FROM productEco")
    fun findAll(): Flow<List<ProductModel>>

    @Query("SELECT * FROM productEco WHERE id = :id")
    fun findById(id: Long): Flow<ProductModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg productEco: ProductModel)

    @Delete
    fun delete(vararg productEco: ProductModel)

    @Update
    fun update(vararg productEco: ProductModel)

}