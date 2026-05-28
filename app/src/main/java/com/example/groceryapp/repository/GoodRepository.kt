package com.example.groceryapp.repository

import com.example.groceryapp.data.DiscountEntity
import com.example.groceryapp.data.NewProductEntity
import com.example.groceryapp.main_model.Category
import com.example.groceryapp.main_model.Good
import kotlinx.coroutines.flow.Flow

interface GoodRepository {
    fun getGoodsById(id: Int): Good?

    fun getAllGoods(): List<Good>

    fun getGoodsByCategory(category: Category): List<Good>

    fun getAllNewGoods(): Flow<List<Int>>

    fun getDiscountsOfGoods(): Flow<Map<Int, Int>>

    suspend fun syncMarketingData()

}
