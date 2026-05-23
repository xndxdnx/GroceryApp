package com.example.groceryapp.favorites.repository

import com.example.groceryapp.favorites.data.FavoriteProductEntity
import com.example.groceryapp.favorites.data.FavoritesDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavoritesRepositoryImpl @Inject constructor(
    private val favDao: FavoritesDao,
    private val favFirestore : FirebaseFirestore
) : FavoritesRepository {

    override fun getAllFavorites(userId: String): Flow<List<Int>> {

        return favDao.getFavoritesByUserId(userId = userId)
    }

    override suspend fun addToFavorites(userId: String, productId: Int) {
        ///////// Room часть
        val favEntity =  FavoriteProductEntity(userId = userId, productId = productId)
        favDao.addToFavorites(favEntity)
        ////////  Firestore часть
        favFirestore.collection("users")
            .document(userId)
            .collection("favs")
            .document(productId.toString()) // Firebase принимает только String в качестве ID
            .set(mapOf("productId" to productId))
    }

    override suspend fun deleteFromFavorites(userId: String, productId: Int) {
        ///////// Room часть
        favDao.deleteFromFavorites(userId = userId, productId = productId)
        ////////  Firestore часть
        favFirestore.collection("users")
            .document(userId)
            .collection("favs")
            .document(productId.toString())
            .delete() // Просто стираем этот документ с сервера
    }

    override suspend fun clearFavorites(userId: String) {
        favDao.clearAllFavorites(userId = userId)
    }


}