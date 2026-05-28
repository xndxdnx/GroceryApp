package com.example.groceryapp.repository


import androidx.compose.ui.graphics.Color
import com.example.groceryapp.R
import com.example.groceryapp.data.DiscountEntity
import com.example.groceryapp.data.MarketingDao
import com.example.groceryapp.data.NewProductEntity
import com.example.groceryapp.main_model.Category
import com.example.groceryapp.main_model.Good
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.collections.find
import kotlin.collections.flatten


class GoodRepositoryImpl @Inject constructor(
    private val marketingDao: MarketingDao
) : GoodRepository {

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
                imageResId = R.drawable.pome_granate_png,
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


    override fun getAllNewGoods(): Flow<List<Int>> {
        return marketingDao.getNewPoints().map { list ->
            list.map { item ->
                item.productId

            }
        }
    }

    override fun getDiscountsOfGoods():  Flow<Map<Int, Int>> {
        return marketingDao.getDiscounts().map { list ->
            list.associate { item ->
                item.productId to item.discountValue

            }
        }
    }

    override suspend fun syncMarketingData() {

        val firestore = FirebaseFirestore.getInstance()

        val discountsRef = firestore.collection("discounts")
        try {
            val snapshot = discountsRef.get().await()
            val discountEntities = snapshot.documents.map { document ->
                DiscountEntity(
                    productId = document.id.toInt(),
                    discountValue = document.getLong("discountValue")?.toInt() ?: 0
                )
            }
            marketingDao.insertDiscounts(discountEntities)

            val newProductSnapshot = firestore.collection("new_products").get().await()

            val newProductEntities = newProductSnapshot.documents.map{ document ->
                NewProductEntity(
                    productId = document.id.toInt()
                )
            }
            marketingDao.insertNewProducts(newProductEntities)

        } catch (e: Exception) {
            e.printStackTrace()
        }




    }






}