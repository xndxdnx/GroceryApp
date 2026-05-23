package com.example.groceryapp.cart.data

import android.R
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.groceryapp.cart.repository.CartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartEntity: CartProductEntity)

    @Query("DELETE FROM cart_products  WHERE userId = :userId AND productId = :productId")
    suspend fun deleteFromCart(userId: String, productId: Int)

   @Query("SELECT * FROM cart_products WHERE userId = :userId")
    fun getCartByUserId(userId: String): Flow<List<CartProductEntity>>

    @Query("DELETE FROM cart_products WHERE userId = :userId")
    suspend fun clearCartByUserId(userId: String)

}