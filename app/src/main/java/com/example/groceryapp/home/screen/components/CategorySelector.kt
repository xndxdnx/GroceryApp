package com.example.groceryapp.home.screen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.main_model.Category
import com.example.groceryapp.ui.theme.greyColor

@Composable
fun CategorySelector(
      onCategorySelected: (Category) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ) {
        items(Category.entries.toTypedArray()) { item ->
            CategoryItem(
                category = item,
                onCategorySelected = onCategorySelected
            )
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    onCategorySelected: (Category) -> Unit
) {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(15.dp))
            .clickable{
                onCategorySelected(category)
            }
    ) {
        Icon(
            painter = painterResource(category.iconResId),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .align(alignment = Alignment.TopCenter)
                .size(55.dp)
                .background(
                    color = category.color.copy(alpha = 0.15f),
                    shape = CircleShape
                )
                .padding(10.dp)
        )
        Row(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category.displayName,
                fontSize = 12.sp,
                color = greyColor
            )
        }
    }


}