package com.example.groceryapp.preview_splash_screens.screens

import android.R
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.groceryapp.preview_splash_screens.data.SplashScreensViewModel
import com.example.groceryapp.ui.theme.backgroundColor1
import com.example.groceryapp.ui.theme.backgroundColor2
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ThirdSplashScreen(
    viewModel: SplashScreensViewModel = hiltViewModel(),
    onButtonClick: () -> Unit = {},
    onButtonScipClick: () -> Unit = {},
) {
    val pageState = rememberPagerState(pageCount = { 3 })

    val scope = rememberCoroutineScope()
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
            .fillMaxSize()
    ) {
        HorizontalPager(state = pageState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = whiteColor),

                ) {
                Image(
                    painter = painterResource(viewModel.thirdScreenImages[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 220.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.BottomCenter)
                        .padding(bottom = 120.dp),

                    ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = viewModel.thirdScreenTitle[page],
                            fontSize = 30.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = blackColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 32.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = viewModel.description,
                            fontSize = 15.sp,
                            color = greyColor,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )


                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align (alignment = Alignment.BottomCenter)
                .padding(bottom = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Skip",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = greyColor,
                    modifier = Modifier
                        .padding(end = 80.dp)
                        .clickable {
                            onButtonScipClick()
                        }
                )
                Row(
                    modifier = Modifier
                        .size(width = 60.dp, height = 10.dp),
                    verticalAlignment = Alignment.Bottom

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
                Text(
                    text = "Next",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryDarkColor,
                    modifier = Modifier
                        .padding(start = 80.dp)
                        .clickable {
                            if (pageState.currentPage == pageState.pageCount - 1) {
                                onButtonClick()
                            } else {
                                scope.launch {
                                    pageState.animateScrollToPage(
                                        page = pageState.currentPage + 1,
                                        animationSpec = tween(
                                            durationMillis = 800,
                                            easing = FastOutSlowInEasing
                                        )
                                    )
                                }
                            }
                        }
                )
            }


        }
    }

}