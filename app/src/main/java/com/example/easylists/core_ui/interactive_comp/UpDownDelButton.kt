package com.example.easylists.core_ui.interactive_comp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.easylists.R

@Composable
fun UpDownDelButton(moreThanOne: Boolean, upClick: () -> Unit, downClick: () -> Unit) {
    // Up count button
    CustomIconButton(
        imageVector = Icons.Filled.KeyboardArrowUp,
        description = stringResource(id = R.string.up_button),
        onClick = { upClick() })

    // Down count button
    CustomIconButton(
        imageVector = if (moreThanOne) Icons.Filled.KeyboardArrowDown
        else Icons.Filled.Delete,
        description = stringResource(id = R.string.down_button),
        onClick = { downClick() })
}