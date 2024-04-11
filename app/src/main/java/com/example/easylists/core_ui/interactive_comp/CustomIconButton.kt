package com.example.easylists.core_ui.interactive_comp

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

// ----- COMPOSABLE FOR A SIMPLE STYLED IMAGE BUTTON -----
@Composable
fun CustomIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    description: String? = null,
    buttonEnabler: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        enabled = buttonEnabler,
        onClick = { onClick() },
        modifier = modifier,
        colors = IconButtonColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            contentColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.LightGray
        )
    ) {
        Icon(
            modifier = Modifier.scale(1.4f),
            imageVector = imageVector,
            contentDescription = description
        )
    }
}