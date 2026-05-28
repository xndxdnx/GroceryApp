package com.example.groceryapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.groceryapp.cart.data.CartDao
import com.example.groceryapp.cart.data.CartProductEntity
import com.example.groceryapp.favorites.data.FavoriteProductEntity
import com.example.groceryapp.favorites.data.FavoritesDao

@Database(
    entities = [
        FavoriteProductEntity::class,
        CartProductEntity::class,
        DiscountEntity::class,
        NewProductEntity::class

               ],
    version = 3
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun getFavoritesDao(): FavoritesDao

    abstract fun getCartDao(): CartDao

    abstract fun getMarketingDao(): MarketingDao

}