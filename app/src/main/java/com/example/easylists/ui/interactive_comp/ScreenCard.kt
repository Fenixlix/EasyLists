package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
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
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp)
                .background(MaterialTheme.colors.background,
                    shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp))
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
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.verticalGradient(
                                0.0f to Color.Transparent,
                                0.5f to MaterialTheme.colors.background,
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