package com.example.groceryapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.groceryapp.cart.data.CartProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MarketingDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiscounts(discounts: List<DiscountEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewProducts(newProducts: List<NewProductEntity>)


    @Query("SELECT * FROM new_products")
    fun getNewPoints(): Flow<List<NewProductEntity>>

    @Query("SELECT * FROM discount_products")
    fun getDiscounts(): Flow<List<DiscountEntity>>


}