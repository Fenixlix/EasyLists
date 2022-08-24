package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.model.data_types.ShopMultiItem
import com.example.easylists.ui.informative_comp.CustomText
import com.example.easylists.ui.theme.PromotionColors

@Composable
fun ShopMultiItemList(
    listItem: ShopMultiItem,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onUpButtonClick: () -> Unit,
    onDownButtonClick: () -> Unit,
    onValueClick: (ShopMultiItem) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = if (listItem.promotionCode >= 0) 1.dp
                else 2.dp,
                color = if (listItem.promotionCode >= 0) PromotionColors[listItem.promotionCode]
                else MaterialTheme.colors.secondaryVariant
            )
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {

        // Component Name
        CustomText(
            text = listItem.name,
            modifier = Modifier.weight(0.2f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Total items count
        CustomText(
            text = "${listItem.quantity.value} X",
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Component value
        CustomText(
            text = "$ ${listItem.price}",
            modifier = Modifier
                .weight(0.2f)
                .clickable { onValueClick(listItem) },
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Component Total value
        Box(
            modifier = Modifier
                .weight(0.3f)
                .height(40.dp)
                .padding(4.dp)
                .background(
                    MaterialTheme.colors.secondaryVariant.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            CustomText(
                text = "$ ${listItem.price * listItem.quantity.value}",
                modifier= Modifier.background(Color.Transparent),
                fontSize = fontSize,
                fontWeight = fontWeight,
                textAlign = TextAlign.Center
            )
        }
        // Up count button
        CustomIconButton(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
            description = "A Button for add a count to this item",
            click = { onUpButtonClick() })

        // Down count button
        CustomIconButton(
            painter = painterResource(
                id = if (listItem.quantity.value > 0) R.drawable.ic_baseline_arrow_downward_24
                else R.drawable.ic_delete_24
            ),
            description = "A Button for reduce this item count",
            click = { onDownButtonClick() })
    }
}
