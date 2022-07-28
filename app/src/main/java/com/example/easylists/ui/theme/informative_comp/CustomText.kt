package com.example.easylists.ui.theme.informative_comp

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// ----- COMPOSABLE FOR THE STYLED TEXT DATA DISPLAYS -----
@Composable
fun CustomText(text : String,
               modifier : Modifier = Modifier,
               color: Color = MaterialTheme.colors.primary,
               fontWeight: FontWeight = FontWeight.Normal,
               fontSize: TextUnit = 16.sp,
               textAlign: TextAlign = TextAlign.Center
){
    Text(
        text = text,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier)
}