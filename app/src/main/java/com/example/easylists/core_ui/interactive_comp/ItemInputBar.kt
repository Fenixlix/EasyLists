package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text2.input.TextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.easylists.R
import com.example.easylists.core_ui.decorative_comp.PrettyVerticalWideSpacer

// ----- COMPOSABLE FOR THE INPUT OF THE DATA BY THE USER -----
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemInputBar(
    textField: TextFieldState,
    modifier: Modifier = Modifier,
    textPlaceholder: String = "",
    numberField: TextFieldState? = null,
    buttonEnabler: Boolean = true,
    onButtonClick: () -> Unit
) {

    val textFocusRequester = remember { FocusRequester }
    val numberFocusRequester = remember { FocusRequester }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // ~~~~~ Name of the Item
        CustomInputTextField(
            modifier = Modifier
                .weight(2f)
                .padding(start = 8.dp)
                .focusRequester(textFocusRequester.Default),
            textFieldState = textField,
            placeholder = textPlaceholder,
            keyboardActions = if (numberField != null && numberField.text.isBlank()) {
                KeyboardActions(
                    onNext = {
                        numberFocusRequester.Default.requestFocus()
                    }
                )
            } else {
                KeyboardActions(onDone = {
                    if (buttonEnabler) onButtonClick()
                })
            }
        )

        // ~~~~~ Value of the Item
        if (numberField != null)
            CustomInputTextField(
                textFieldState = numberField,
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(numberFocusRequester.Default),
                keyboardType = KeyboardType.Number,
                customInputTransformation = MoneyInputTransformation,
                keyboardActions = if (textField.text.isBlank()) {
                    KeyboardActions(onPrevious = {
                        textFocusRequester.Default.requestFocus()
                    })
                } else {
                    KeyboardActions(onDone = {
                        if (buttonEnabler) onButtonClick()
                    })
                }
            )

        // ~~~~~ Button for add the Item
        CustomIconButton(
            imageVector = Icons.Filled.Add,
            description = stringResource(id = R.string.add_button),
            buttonEnabler = buttonEnabler,
            onClick = { onButtonClick() })
    }

    PrettyVerticalWideSpacer()

}

