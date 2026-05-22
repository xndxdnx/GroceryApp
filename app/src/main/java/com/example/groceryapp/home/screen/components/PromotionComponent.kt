package com.example.groceryapp.home.screen.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject


@HiltViewModel
class HomePromotionViewModel @Inject constructor(
) : ViewModel() {
    val promotionImages = listOf(
        R.drawable.splash_1_1,
        R.drawable.promotion_splash_1,
        R.drawable.splash_1_2,
        R.drawable.splash_1_3,
        R.drawable.splash_1_4,
    )

    val promotionTitles = listOf(
        "Welcome",
        "20% off on your\n" +
                "first purchase",
        "Buy Quality \n" +
                "Dairy Products",
        "Buy Premium\n" +
                "Quality Fruits",
        "Get Discounts \n" +
                "On All Products"

    )
}



@Composable
fun PromotionComponent(
    viewModel: HomePromotionViewModel = hiltViewModel(),

    ) {
    val pageState = rememberPagerState(pageCount = { 5 })

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            val nextPage = (pageState.currentPage + 1) % pageState.pageCount
            if (!pageState.isScrollInProgress) {
                pageState.animateScrollToPage(
                    page = nextPage,
                    animationSpec = tween(
                        durationMillis = 800,
                        easing = FastOutSlowInEasing
                    )
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(5.dp)),

    ) {
        HorizontalPager(state = pageState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(viewModel.promotionImages[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterStart)
                        .padding(top = 40.dp, start = 50.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = viewModel.promotionTitles[page],
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = blackColor,
                        textAlign = TextAlign.Start,
                        lineHeight = 32.sp
                    )
                }


            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp, start = 50.dp)
                .align(alignment = Alignment.BottomStart),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(times = pageState.pageCount) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(10.dp)
                        .background(
                            color =
                                if (it == pageState.currentPage) {
                                    primaryDarkColor
                                } else {
                                    greyColor
                                },
                            shape = CircleShape
                        )

                )
            }
        }


    }
}





