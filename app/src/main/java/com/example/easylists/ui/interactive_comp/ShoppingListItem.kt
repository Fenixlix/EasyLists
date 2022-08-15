package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.model.data_types.ShopItem
import com.example.easylists.ui.theme.PromotionColors
import com.example.easylists.ui.informative_comp.CustomText

// ----- COMPOSABLE FOR THE RESUME AND THE VARIOUS ITEMS OF THE LIST -----
@Composable
fun ShoppingListItem(
    listItem: ShopItem,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    totItems: Int? = null,
    buttonDrawable: Painter? = null,
    onButtonClick: () -> Unit
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Total items count
        if (totItems != null) {
            CustomText(
                text = totItems.toString(),
                fontSize = fontSize,
                fontWeight = fontWeight
            )
        }

        // Component Name
        CustomText(
            text = listItem.name,
            modifier = Modifier.weight(0.5f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Component value
        CustomText(
            text = "$ ${listItem.value}",
            modifier = Modifier.weight(0.4f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Draw a button with the given functionality
        if (buttonDrawable != null) {
            CustomIconButton(
                painter = buttonDrawable,
                description = "A Button for delete this item",
                click = { onButtonClick() })
        }
    }
}
