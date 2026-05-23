package com.example.groceryapp.DI

import com.example.groceryapp.cart.repository.CartRepository
import com.example.groceryapp.cart.repository.CartRepositoryImpl
import com.example.groceryapp.favorites.repository.FavoritesRepository
import com.example.groceryapp.favorites.repository.FavoritesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindFavoritesRepository(impl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds @Singleton
    abstract fun bindCartRepository(impl: CartRepositoryImpl): CartRepository
}