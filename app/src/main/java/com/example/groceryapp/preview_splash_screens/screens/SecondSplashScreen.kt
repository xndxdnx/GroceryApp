package com.example.groceryapp.preview_splash_screens.screens

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.groceryapp.R
import com.example.groceryapp.preview_splash_screens.data.SplashScreensViewModel
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor
import com.example.groceryapp.ui.theme.primaryDarkColor
import com.example.groceryapp.ui.theme.whiteColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SecondSplashScreen(
    viewModel: SplashScreensViewModel = hiltViewModel(),
    onButtonClick: () -> Unit = {}
) {
    val pageState = rememberPagerState(pageCount = { 4 })

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

    val CurvedTopShape = GenericShape { size, _ ->
        // Создаем переменную для высоты изгиба арки
        val arcHeight = 120f

        // 1. Стартуем: перемещаем карандаш в левый нижний угол
        moveTo(0f, size.height)

        // 2. Чертим линию вверх по левому краю, недоезжая до верха на высоту арки
        lineTo(0f, arcHeight)

        // 3. Рисуем купол с помощью кривой Безье
        quadraticBezierTo(
            x1 = size.width / 2f, y1 = -arcHeight, // "Магнит" по центру, уходящий в минус по Y
            x2 = size.width, y2 = arcHeight   // Конечная точка дуги на правом краю
        )

        // 4. Опускаем линию вниз по правому краю
        lineTo(size.width, size.height)

        // 5. Закрываем контур (автоматическая линия к старту)
        close()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HorizontalPager(state = pageState) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(viewModel.secondScreenImages[page]),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,

                    )

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .align(Alignment.BottomCenter),
                    color = whiteColor,
                    shape = CurvedTopShape,

                    ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 66.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = viewModel.secondScreenTitle[page],
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
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align (alignment = Alignment.BottomCenter)
                .padding(bottom = 50.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp),
                horizontalArrangement = Arrangement.Center,
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
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
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primaryDarkColor
                    ),
                    elevation = ButtonDefaults.buttonElevation(5.dp),
                    modifier = Modifier
                        .size(height = 60.dp, width = 380.dp),
                    shape = RoundedCornerShape(5.dp)

                ) {
                    Text(
                        text = "Get Started",
                        lineHeight = 23.sp,
                        color = whiteColor,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,

                        )
                }
            }

        }
    }
}