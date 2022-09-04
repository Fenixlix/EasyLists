package com.example.easylists.ui.interactive_comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.easylists.R
import com.example.easylists.ui.informative_comp.CustomText

@Composable
fun CustomTopAppBar(modifier: Modifier = Modifier, onHomeClick: () -> Unit) {
    TopAppBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(), backgroundColor = MaterialTheme.colors.secondaryVariant
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Home button
            IconButton(onClick = { onHomeClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_home_24),
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
                        .clickable { onTitleChange("Nuevo título") },
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.background)

                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = { /*TODO*/ }, modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_delete_24),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}