package com.example.groceryapp.home.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.blackColor
import com.example.groceryapp.ui.theme.greyColor

@Composable
fun FeaturedRow(
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Featured products",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = blackColor,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f)

        )

        Icon(
            painter = painterResource(R.drawable.right_arrov_icon),
            contentDescription = null,
            tint = greyColor,

            modifier = Modifier
                .size(width = 20.dp, height = 22.dp)
        )
    }


}