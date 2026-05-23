package com.example.groceryapp.cart.repository

import com.example.groceryapp.cart.data.CartDao
import com.example.groceryapp.cart.data.CartProductEntity
import com.example.groceryapp.home.screen.components.CategoryItem
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val cartFirestore: FirebaseFirestore
) : CartRepository {
    override suspend fun addToCart(userId: String, productId: Int, quantity: Int) {
        // Room часть
        val cartEntity = CartProductEntity(userId = userId, productId = productId, quantity = quantity)
        cartDao.addToCart(cartEntity)
        // Firestore часть
        cartFirestore.collection("users")
            .document(userId)
            .collection("cart")
            .document(productId.toString()) // Firebase принимает только String в качестве ID
            .set(
                mapOf(
                    "productId" to productId,
                    "quantity" to quantity
                )
            )
    }

    override suspend fun deleteFromCart(userId: String, productId: Int) {
        // Room часть
        cartDao.deleteFromCart(userId = userId, productId = productId)
        // Firebase часть
        cartFirestore.collection("users")
            .document(userId)
            .collection("cart")
            .document(productId.toString()) // Firebase принимает только String в качестве ID
            .delete()
    }

    override fun getAllCart(userId: String): Flow<List<CartItem>> {
        return cartDao.getCartByUserId(userId = userId).map { entitiesList ->
            entitiesList.map { entity ->
                CartItem(
                    productId = entity.productId,
                    quantity = entity.quantity
                )
            }
        }
    }

    override suspend fun clearCart(userId: String) {
        cartDao.clearCartByUserId(userId = userId)
    }
}