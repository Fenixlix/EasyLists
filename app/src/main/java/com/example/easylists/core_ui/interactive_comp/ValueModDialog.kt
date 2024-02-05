package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.easylists.R
import com.example.easylists.core_ui.informative_comp.CustomText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ValueModDialog(
    showDialog: Boolean,
    itemValue: Float,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onOkClick: (Float) -> Unit,
) {

    if (showDialog) {
        val itemVal = remember { mutableStateOf(itemValue) }
        val keyboardController = LocalSoftwareKeyboardController.current

        AlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = modifier
                .size(width = 300.dp, height = 200.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colors.secondaryVariant,
                    shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(6.dp),
            backgroundColor = MaterialTheme.colors.background,
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CustomText(
                        text = stringResource(id = R.string.dialog_title),
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            },
            shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp),
            properties = DialogProperties(),
            text = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    InputTextField(
                        fieldText = itemVal.value.toString(),
                        modifier = Modifier.padding(4.dp),
                        onTextChange = {
                            try {
                                itemVal.value = it.toFloat()
                            } catch (e: Exception) {
                                itemVal.value = itemValue
                            }
                        },
                        textAlign = TextAlign.Center,
                        placeholder = "",
                        keyboardType =  KeyboardType.Number,
                        keyboardController = keyboardController
                    )
                }
            },
            buttons = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = { onOkClick(itemVal.value) },
                    ) {
                        CustomText(
                            text = stringResource(id = R.string.dialog_ok_button),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.background
                        )
                    }
                }
            }
        )
    }
}

