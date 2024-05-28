package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ----- COMPOSABLE FOR THE INPUT TEXT ELEMENTS -----
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomInputTextField(
    textFieldState: TextFieldState,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    customInputTransformation: InputTransformation? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    fontSize: TextUnit = 18.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Center
) {
    BasicTextField2(
        state = textFieldState,
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            textAlign = textAlign,
            fontSize = fontSize,
            fontWeight = fontWeight
        ),
        cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
        decorator = { textField ->
            Column(
                Modifier.height(48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )

                Box {
                    textField()
                    if (textFieldState.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.secondary)
                        .height(2.dp)
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = keyboardActions,
        inputTransformation = customInputTransformation
    )
}

@OptIn(ExperimentalFoundationApi::class)
object MoneyInputTransformation : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (valueWithChanges.toString().isEmpty()) {
            valueWithChanges.replace(0, 0, "0")
        }
        if (!valueWithChanges.toString().matches("(\\d*.)?\\d+".toRegex())) {
            valueWithChanges.revertAllChanges()
        }
    }
}