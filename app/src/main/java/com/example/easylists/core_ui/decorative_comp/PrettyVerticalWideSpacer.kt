package com.example.easylists.core_ui.decorative_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// ----- COMPOSABLE FOR A COLOR GRADIENT LINE SPACER -----
@Composable
fun PrettyVerticalWideSpacer(modifier: Modifier = Modifier, height : Dp = 4.dp){
    val finalHeight = if ( height <= 0.dp ) 4.dp else height

    Spacer(modifier = modifier.height(finalHeight))
    Spacer(modifier = modifier
        .fillMaxWidth()
        .height(finalHeight)
        .background(brush = Brush.horizontalGradient(
            0.0f to MaterialTheme.colors.background,
            0.2f to MaterialTheme.colors.primary,
            0.8f to MaterialTheme.colors.secondary,
            1.0f to MaterialTheme.colors.background)
        )
    )
    Spacer(modifier = modifier.height(finalHeight))
}

