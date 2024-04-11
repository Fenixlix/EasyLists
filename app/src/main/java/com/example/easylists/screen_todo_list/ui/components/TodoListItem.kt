package com.example.easylists.screen_todo_list.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.easylists.R
import com.example.easylists.core_ui.informative_comp.CustomText
import com.example.easylists.core_ui.interactive_comp.CustomIconButton
import com.example.easylists.screen_todo_list.model.data_types.TodoItem

@Composable
fun TodoListItem(
    task: TodoItem,
    modifier: Modifier = Modifier,
    onCheck: (TodoItem) -> Unit,
    onClickDelete: (TodoItem) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp, vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomText(
            text = task.task,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .background(
                    brush = Brush.horizontalGradient(
                        0.0f to MaterialTheme.colorScheme.background,
                        0.1f to MaterialTheme.colorScheme.background,
                        0.8f to MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                        1.0f to MaterialTheme.colorScheme.secondary
                    ),
                    shape = CircleShape
                )
                .padding(8.dp)
                .padding(end = 12.dp),
            textAlign = TextAlign.Justify
        )

        Checkbox(
            checked = task.isChecked,
            modifier = Modifier
                .weight(0.125f)
                .scale(1.2f)
                .padding(horizontal = 4.dp),
            onCheckedChange = { onCheck(task) })

        if (task.isChecked)
            Box(modifier = Modifier.weight(0.125f)) {
                CustomIconButton(
                    imageVector = Icons.Filled.Delete,
                    description = stringResource(id = R.string.delete_button)
                ) {
                    onClickDelete(task)
                }
            }
    }
}