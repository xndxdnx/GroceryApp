package com.example.groceryapp.repository


import androidx.compose.ui.graphics.Color
import com.example.groceryapp.R
import com.example.groceryapp.model.Category
import com.example.groceryapp.model.Good
import javax.inject.Inject
import kotlin.collections.find
import kotlin.collections.flatten


class GoodRepositoryImpl @Inject constructor() : GoodRepository {

    private val goods = mapOf(
        Category.VEGETABLES to listOf(
            Good(
                id = 1,
                title = "Fresh Broccoli",
                price = 3.00,
                description = "",
                imageResId = R.drawable._fresh_broccoli_png,
                weightTitle = "1 kg",
                accentColor = Color(0xFFD2FFD0)
            ),
            Good(
                id = 2,
                title = "Fresh Potato",
                price = 2.00,
                description = "",
                imageResId = R.drawable.potato_png,
                weightTitle = "1 kg",
                accentColor = Color(0xFFFFF3D0)
            ),
        ),
        Category.FRUITS to listOf(
            Good(
                id = 3,
                title = "Fresh Peach",
                price = 8.00,
                description = "",
                imageResId = R.drawable.fresh_peach_png,
                weightTitle = "dozen",
                accentColor = Color(0xFFFFCEC1)
            ),
            Good(
                id = 4,
                title = "Avocado",
                price = 7.00,
                description = "",
                imageResId = R.drawable.avocado_png,
                weightTitle = "2.0 lbs",
                accentColor = Color(0xFFFCFFD9)
            ),
            Good(
                id = 5,
                title = "Pineapple",
                price = 9.90,
                description = "",
                imageResId = R.drawable.pineapple_png,
                weightTitle = "1.5 lbs",
                accentColor = Color(0xFFFFE6C2)
            ),
            Good(
                id = 6,
                title = "Black Grapes",
                price = 7.05,
                description = "",
                imageResId = R.drawable.black_grapes_png,
                weightTitle = "5.0 lbs",
                accentColor = Color(0xFFFEE1ED)
            ),
            Good(
                id = 7,
                title = "Pome Granate",
                price = 2.09,
                description = "",
                imageResId = R.drawable.black_grapes_png,
                weightTitle = "1.5 lbs",
                accentColor = Color(0xFFFFE3E2)
            ),
        )
    )


    override fun getGoodsById(id: Int): Good? {
        return goods.values.flatten().find { item -> item.id == id }
    }

    override fun getAllGoods(): List<Good> {
        return goods.values.flatten()
    }

    override fun getGoodsByCategory(category: Category): List<Good> {
       return goods[category] ?: emptyList()
    }


}