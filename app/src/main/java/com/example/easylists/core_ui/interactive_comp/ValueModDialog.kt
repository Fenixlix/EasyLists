package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.easylists.R
import com.example.easylists.core_ui.informative_comp.CustomText

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ValueModDialog(
    showDialog: Boolean,
    itemValue: Float,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onOkClick: (Float) -> Unit,
) {

    if (showDialog) {
        val itemVal = rememberTextFieldState(itemValue.toString())

        BasicAlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = modifier
                .size(width = 300.dp, height = 200.dp)
                .border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CutCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(6.dp),
            properties = DialogProperties(),

            ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceAround
            ) {
                //Title
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
                // Text
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    CustomInputTextField(
                        textFieldState = itemVal,
                        modifier = Modifier.padding(4.dp),
                        textAlign = TextAlign.Center,
                        customInputTransformation = MoneyInputTransformation,
                        keyboardType = KeyboardType.Number
                    )
                }
                // Buttons
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            onOkClick(
                                itemVal.toString().toFloat()
                            )
                        }
                    ) {
                        CustomText(
                            text = stringResource(id = R.string.dialog_ok_button),
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        }
    }
}

