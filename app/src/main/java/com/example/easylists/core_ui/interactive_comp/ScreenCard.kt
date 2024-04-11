package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ----- Composable for the main screen grid of screens cards buttons
@Composable
fun ScreenCard(
    title: String,
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        border = BorderStroke(width = 4.dp, color = MaterialTheme.colorScheme.secondary),
        shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
                .background(
                    MaterialTheme.colorScheme.background,
                    shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(8.dp)
                .clickable { onItemClick() },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                0.0f to Color.Transparent,
                                0.5f to MaterialTheme.colorScheme.background,
                                1.0f to Color.Transparent
                            ),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                )
            }
        }
    }
}