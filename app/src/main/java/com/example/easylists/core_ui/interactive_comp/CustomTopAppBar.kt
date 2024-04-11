package com.example.easylists.core_ui.interactive_comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.easylists.core_ui.informative_comp.CustomText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier, onHomeClick: () -> Unit) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    title = {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Home button
            IconButton(onClick = { onHomeClick() }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home button"
                )
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Title of the saved file.
                val (title, onTitleChange) = remember { mutableStateOf("Title") }
                CustomText(text = title,
                    modifier = Modifier
                        .weight(2f)
                        .clickable { onTitleChange("New Title") },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.background)

                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = ""
                    )
                }
            }
        }
    })
}
