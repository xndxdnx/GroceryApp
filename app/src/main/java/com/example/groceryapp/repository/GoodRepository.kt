package com.example.groceryapp.repository

import com.example.groceryapp.model.Category
import com.example.groceryapp.model.Good

interface GoodRepository {
    fun getGoodsById(id: Int): Good?
    fun getAllGoods(): List<Good>

    fun getGoodsByCategory(category: Category): List<Good>
}
