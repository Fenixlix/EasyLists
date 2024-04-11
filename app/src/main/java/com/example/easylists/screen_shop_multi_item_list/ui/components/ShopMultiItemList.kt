package com.example.easylists.screen_shop_multi_item_list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.core_ui.informative_comp.CustomText
import com.example.easylists.core_ui.interactive_comp.UpDownDelButton
import com.example.easylists.core_ui.theme.PromotionColors
import com.example.easylists.screen_shop_multi_item_list.model.data_types.ShopMultiItem

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
                else MaterialTheme.colorScheme.secondary
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
            text = "${listItem.quantity} X",
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Component value
        CustomText(
            text = listItem.price.toString(),
            modifier = Modifier
                .weight(0.2f)
                .clickable { onValueClick(listItem) },
            inMoneyFormat = true,
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Component Total value
        Box(
            modifier = Modifier
                .weight(0.3f)
                .height(40.dp)
                .padding(horizontal = 2.dp)
                .padding(vertical = 4.dp)
                .background(
                    MaterialTheme.colorScheme.secondary.copy(alpha = 0.4f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            CustomText(
                text = "${listItem.price * listItem.quantity}",
                modifier = Modifier.background(Color.Transparent),
                inMoneyFormat = true,
                fontSize = fontSize,
                fontWeight = fontWeight,
                textAlign = TextAlign.Center
            )
        }

        UpDownDelButton(
            moreThanOne = listItem.quantity > 1,
            upClick = { onUpButtonClick() },
            downClick = { onDownButtonClick() }
        )
    }
}
