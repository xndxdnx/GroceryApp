package com.example.groceryapp.DI

import com.example.groceryapp.data.MarketingDao
import com.example.groceryapp.favorites.repository.FavoritesRepositoryImpl
import com.example.groceryapp.repository.GoodRepository
import com.example.groceryapp.repository.GoodRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth () : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore () : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun  provideGoodRepository(marketingDao: MarketingDao) : GoodRepository {
        return GoodRepositoryImpl(marketingDao)
    }


}