package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

// ----- COMPOSABLE FOR THE INPUT TEXT ELEMENTS -----
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    fieldText: String,
    onTextChange: (String) -> Unit,
    placeholder: String,
    keyboardController: SoftwareKeyboardController?,
    keyboardType : KeyboardType = KeyboardType.Number,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start
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
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
        })
    )
}