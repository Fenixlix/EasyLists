package com.example.easylists.core_ui.interactive_comp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

// ----- COMPOSABLE FOR A SIMPLE STYLED IMAGE BUTTON -----
@Composable
fun CustomIconButton(
    painter: Painter,
    description: String,
    modifier: Modifier = Modifier,
    buttonEnabler: Boolean = true,
    click: () -> Unit
) {
    val backGroundColor by animateColorAsState(
        targetValue = if (buttonEnabler) MaterialTheme.colors.primary else Color.DarkGray
    )

    IconButton(
        enabled = buttonEnabler,
        onClick = { click() },
        modifier = modifier
    ) {
        Image(
            painter = painter,
            contentDescription = description,
            modifier = Modifier
                .background(
                    color = backGroundColor,
                    shape = MaterialTheme.shapes.small.copy(all = CornerSize(50))
                )
                .padding(8.dp)
        )
    }
}