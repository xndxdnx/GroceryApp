package com.example.groceryapp.main.screen.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.groceryapp.R
import com.example.groceryapp.main.data.HomePromotionViewModel
import com.example.groceryapp.preview_splash_screens.data.SplashScreensViewModel
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.borderColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.Async

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
                    painter = painterResource(viewModel.firstScreenImages[page]),
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
                        text = viewModel.firstScreenTitle[page],
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





