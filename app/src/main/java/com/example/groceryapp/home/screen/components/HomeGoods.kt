package com.example.groceryapp.home.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.groceryapp.R
import com.example.groceryapp.home.data.HomeViewModel
import com.example.groceryapp.model.Good
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.backgroundColor2
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor


@Composable
fun HomeGoods(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val goods by viewModel.uiState.collectAsStateWithLifecycle()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
        , modifier = Modifier
            .height(900.dp)

    ) {
        items(items = goods.goods, key = { it.id }) { item ->
                GoodCard(
                    good = item,
                    isFav = true
                )
        }
    }
}

@Composable
fun GoodCard(
    good: Good,
    isFav: Boolean,
    onToggleFavorite: () -> Unit = {},
    onToggleCard: () -> Unit = {}
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(good.imageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .background(
                            color = good.accentColor.copy(alpha = 0.3f),
                            shape = CircleShape
                        )

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
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Add to cart",
                        fontSize = 13.sp,
                        color = primaryDarkColor,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 8.dp),
                    )
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
                    painter = if(isFav) {
                        painterResource(R.drawable.heart_fill)
                    }else{
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