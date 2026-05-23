package com.example.groceryapp.favorites.repository

import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun addToFavorites(userId: String, productId: Int)

    suspend fun deleteFromFavorites(userId: String, productId: Int)

    fun getAllFavorites(userId: String): Flow<List<Int>>

    suspend fun clearFavorites(userId: String)

}