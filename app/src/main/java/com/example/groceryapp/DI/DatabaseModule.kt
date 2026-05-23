package com.example.groceryapp.DI

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.groceryapp.cart.data.CartDao
import com.example.groceryapp.data.AppDatabase
import com.example.groceryapp.favorites.data.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun createFavDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            name = "favorite_db", context = context, klass = AppDatabase::class.java
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideFavoritesDao(database: AppDatabase): FavoritesDao {
        return database.getFavoritesDao()
    }

    @Provides
    fun providesCartDao(database: AppDatabase): CartDao {
        return database.getCartDao()
    }

}