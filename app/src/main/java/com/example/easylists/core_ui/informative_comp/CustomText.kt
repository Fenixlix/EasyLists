package com.example.easylists.core_ui.informative_comp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import java.text.NumberFormat

// ----- COMPOSABLE FOR THE STYLED TEXT DATA DISPLAYS -----
@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    inMoneyFormat: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 16.sp,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = if (inMoneyFormat) text.toMoney() else text,
        color = color,
        textAlign = textAlign,
        fontWeight = fontWeight,
        fontSize = fontSize,
        modifier = modifier
    )
}

// Because it is already filtered the data for the text to be like \d+(.\d*)?
// it isn't necessary to confirm it again
private fun String.toMoney() = NumberFormat.getCurrencyInstance().format(this.toDouble())


