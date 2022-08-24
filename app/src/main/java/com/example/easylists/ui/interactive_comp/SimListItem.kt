package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.ui.informative_comp.CustomText

@Composable
fun SimListItem(
    listItemName: String,
    listItemCount: Int,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Normal,
    onUpButtonClick: () -> Unit,
    onDownButtonClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(1.dp, color = MaterialTheme.colors.secondary)
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // Item name
        CustomText(
            text = listItemName,
            modifier = Modifier.weight(0.5f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Item count
        CustomText(
            text = "# $listItemCount",
            modifier = Modifier.weight(0.4f),
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        // Up count button
        CustomIconButton(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_upward_24),
            description = "A Button for add a count to this item",
            click = { onUpButtonClick() })

        // Down count button
        CustomIconButton(
            painter = painterResource(
                id = if(listItemCount > 0) R.drawable.ic_baseline_arrow_downward_24
                else R.drawable.ic_delete_24),
            description = "A Button for reduce this item count",
            click = { onDownButtonClick() })
    }
}
