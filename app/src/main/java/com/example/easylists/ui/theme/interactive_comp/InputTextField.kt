package com.example.easylists.ui.theme.interactive_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// ----- COMPOSABLE FOR THE INPUT TEXT ELEMENTS -----
@Composable
fun InputTextField(
    fieldText: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions { }
) {
    TextField(
        value = fieldText,
        onValueChange = {
            onTextChange(it)
        },
        modifier = modifier.background(MaterialTheme.colors.background),
        placeholder = {
            Text(
                text = placeholder,
                color = MaterialTheme.colors.primaryVariant,
                fontSize = fontSize,
                fontWeight = fontWeight,
                textAlign = textAlign
            )
        },
        textStyle = TextStyle(
            color = MaterialTheme.colors.primary,
            textAlign = textAlign,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions
    )
}