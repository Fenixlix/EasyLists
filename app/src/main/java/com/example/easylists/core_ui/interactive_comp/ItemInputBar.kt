package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.easylists.core_ui.decorative_comp.PrettyVerticalWideSpacer
import com.example.easylists.R

// ----- COMPOSABLE FOR THE INPUT OF THE DATA BY THE USER -----
@OptIn(ExperimentalComposeUiApi::class)
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
    val keyboardController = LocalSoftwareKeyboardController.current

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
            modifier = Modifier.weight(2f),
            fieldText = fieldText,
            onTextChange = { onTextChange(it) },
            placeholder = fieldTextPlaceholder,
            keyboardController = keyboardController
        )

        // ~~~~~ Value of the Item
        if (fieldValue != null)
            InputTextField(
                modifier = Modifier.weight(1f),
                fieldText = if (fieldValue == "0.0") "" else fieldValue,
                onTextChange = { onValueChange?.invoke(it) },
                placeholder = fieldValuePlaceholder ?: "",
                keyboardController = keyboardController
            )

        // ~~~~~ Button for add the Item
        CustomIconButton(painter = buttonDrawable,
            description = stringResource(id = R.string.add_button),
            buttonEnabler = buttonEnabler,
            click = { onButtonClick() })
    }
    PrettyVerticalWideSpacer()
}
