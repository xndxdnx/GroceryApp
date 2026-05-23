package com.example.groceryapp.favorites.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favEntity: FavoriteProductEntity)  // Это я добавляю в бд сущность

    @Query("DELETE FROM favorite_products WHERE userId = :userId AND productId = :productId")
    suspend fun deleteFromFavorites(userId: String, productId: Int)

    @Query("SELECT productId FROM favorite_products WHERE userId = :userId")
    fun getFavoritesByUserId(userId: String): Flow<List<Int>>

    @Query("DELETE FROM favorite_products WHERE userId = :userId")
    suspend fun clearAllFavorites(userId: String)

}