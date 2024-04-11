package com.example.easylists.screen_item_list.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.core_ui.informative_comp.CustomText
import com.example.easylists.core_ui.interactive_comp.CustomIconButton

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
            .border(1.dp, color = MaterialTheme.colorScheme.secondary)
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
            imageVector = Icons.Filled.KeyboardArrowUp,
            description = stringResource(id = R.string.up_button),
            onClick = { onUpButtonClick() })

        // Down count button
        CustomIconButton(
            imageVector = if (listItemCount > 0) Icons.Filled.KeyboardArrowDown
            else Icons.Filled.Delete,
            description = stringResource(id = R.string.down_button),
            onClick = { onDownButtonClick() })
    }
}
