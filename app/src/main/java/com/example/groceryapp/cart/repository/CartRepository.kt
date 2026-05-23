package com.example.groceryapp.cart.repository

import kotlinx.coroutines.flow.Flow

interface CartRepository {

    suspend fun addToCart(userId:String, productId: Int, quantity: Int )

    suspend fun deleteFromCart(userId:String, productId: Int)

    fun getAllCart (userId:String): Flow<List<CartItem>>

    suspend fun clearCart(userId:String)

}