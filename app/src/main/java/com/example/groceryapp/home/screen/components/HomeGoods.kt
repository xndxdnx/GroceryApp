package com.example.groceryapp.home.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.groceryapp.R
import com.example.groceryapp.home.model.HomeViewModel
import com.example.groceryapp.model.Good
import com.example.groceryapp.model.GoodStates
import com.example.groceryapp.ui.theme.backgroundColor2
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor


@Composable
fun HomeGoods(
    goods: GoodStates,
    onFavoriteClick: (productId: Int, isFav: Boolean) -> Unit,
    onAddToCartClick: (productId: Int, currentQty: Int) -> Unit,
    onPlusClick: (productId: Int, currentQty: Int) -> Unit,
    onMinusClick: (productId: Int, currentQty: Int) -> Unit,
    onCardClick: (productId: Int) -> Unit
    ) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier
            .height(900.dp)

    ) {
        items(items = goods.goods, key = { it.id }) { item ->

            val quantityInCart = goods.cartProductsCount[item.id] ?: 0
            val isFavorite = goods.favoriteProductIds.contains(item.id)

            GoodCard(
                good = item,
                isFav = isFavorite,
                goodsInCard = quantityInCart,
                onToggleFavorite = {
                    onFavoriteClick(item.id, isFavorite)
                },
                onToggleAddToCart = {
                    onAddToCartClick(item.id, quantityInCart)
                },
                onTogglePlus = {
                    onPlusClick(item.id, quantityInCart)
                },
                onToggleMinus = {
                    onMinusClick(item.id, quantityInCart)
                },
                onToggleCard = {
                    onCardClick(item.id)
                }

                )
        }
    }
}

@Composable
fun GoodCard(
    good: Good,
    isFav: Boolean,
    onToggleFavorite: () -> Unit,
    goodsInCard: Int,
    onToggleCard: () -> Unit,
    onToggleAddToCart: () -> Unit,
    onTogglePlus: () -> Unit,
    onToggleMinus: () -> Unit,
) {
    Card(
        onClick = onToggleCard,
        modifier = Modifier
            .height(230.dp)
            .width(180.dp),
        colors = CardDefaults.cardColors(whiteColor),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Surface(
                modifier = Modifier
                    .size(85.dp)
                    .align(alignment = Alignment.TopCenter)
                    .padding(top = 15.dp)
                    .aspectRatio(1f),
                shape = CircleShape,
                color = good.accentColor.copy(alpha = 0.7f)
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp,
                        bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(good.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)


                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "$${good.price}",
                        fontSize = 12.sp,
                        color = primaryDarkColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = good.title,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = blackColor
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = good.weightTitle,
                        fontSize = 12.sp,
                        color = greyColor
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = backgroundColor2
                )

                if (goodsInCard <= 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                onToggleAddToCart()
                            },
                    ) {
                        Text(
                            text = "Add to cart",
                            fontSize = 13.sp,
                            color = blackColor,
                            fontWeight = FontWeight.SemiBold,

                            modifier = Modifier
                                .padding(start = 25.dp)
                                .align(alignment = Alignment.Center),
                        )
                        Icon(
                            painter = painterResource(R.drawable.cart_icon2),
                            contentDescription = null,
                            tint = primaryDarkColor,
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(20.dp)
                                .padding(end = 75.dp)
                                .align(
                                    alignment = Alignment.Center
                                )
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onToggleMinus,
                            modifier = Modifier
                                .size(25.dp)
                        ) {
                            Text(
                                text = "-",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = primaryDarkColor
                            )
                        }
                        Text(
                            text = "$goodsInCard",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = blackColor
                        )
                        IconButton(
                            onClick = onTogglePlus,
                            modifier = Modifier
                                .size(25.dp)
                        ) {
                            Text(
                                text = "+",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = primaryDarkColor
                            )
                        }


                    }


                }

            }


            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(end = 5.dp, top = 6.dp)
                    .size(25.dp)
            ) {
                Image(
                    painter = if (isFav) {
                        painterResource(R.drawable.heart_fill)
                    } else {
                        painterResource(R.drawable.favorite_icon)
                    },
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}