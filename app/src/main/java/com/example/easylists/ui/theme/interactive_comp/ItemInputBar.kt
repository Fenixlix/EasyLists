package com.example.easylists.ui.theme.interactive_comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.easylists.ui.theme.decorative_comp.PrettyVerticalWideSpacer

// ----- COMPOSABLE FOR THE INPUT OF THE DATA BY THE USER -----
@Composable
fun ItemInputBar(
    fieldText: String,
    onTextChange: (String) -> Unit,
    fieldTextPlaceholder: String = "",
    fieldValue: String?,
    onValueChange: ((String) -> Unit)?,
    fieldValuePlaceholder: String? = "",
    buttonEnabler: Boolean = true,
    buttonDrawable: Painter,
    onButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 3.dp,
                color = MaterialTheme.colors.secondaryVariant,
                shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
            )
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // ~~~~~ Name of the Item
        InputTextField(
            fieldText = fieldText,
            onTextChange = { onTextChange(it) },
            placeholder = fieldTextPlaceholder,
            modifier = Modifier.weight(2f)
        )

        // ~~~~~ Value of the Item
        if (fieldValue != null)
            InputTextField(
                fieldText = if (fieldValue == "0.0") "" else fieldValue,
                onTextChange = { onValueChange?.invoke(it) },
                placeholder = fieldValuePlaceholder ?: "",
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(onDone = {
                    this.defaultKeyboardAction(ImeAction.Done)
                })
            )

        // ~~~~~ Button for add the Item
        CustomIconButton(painter = buttonDrawable,
            description = "Button for add a new element",
            buttonEnabler = buttonEnabler,
            click = { onButtonClick() })
    }
    PrettyVerticalWideSpacer()
}
