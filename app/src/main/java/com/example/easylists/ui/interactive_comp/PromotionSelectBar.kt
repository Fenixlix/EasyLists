package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.ui.theme.PromotionColors

// ----- COMPOSABLE FOR THE TOP PROMOTION SELECTION BAR -----
@Composable
fun PromotionSelectBar(
    promotions: List<Float>,
    modifier: Modifier = Modifier,
    currentPromotion: Int = 0,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (x in promotions.indices) {
            val isSelected = promotions[x] == promotions[currentPromotion]
            Text(text = " ${(-100 * promotions[x] + 100).toInt()}% ",
                modifier = Modifier
                    .background(
                        color = if (isSelected) PromotionColors[x] else MaterialTheme.colors.background,
                        shape = CircleShape
                    )
                    .clickable { onClick(x) }
                    .padding(horizontal = 2.dp),
                color = if (isSelected) Color.Black else PromotionColors[x],
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp)
        }
    }
}