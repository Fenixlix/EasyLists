package com.example.easylists.ui.theme.interactive_comp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.easylists.R
import com.example.easylists.model.TodoItem
import com.example.easylists.ui.theme.informative_comp.CustomText

@Composable
fun TodoListItem(
    task: TodoItem,
    modifier: Modifier = Modifier,
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
            text = "-}>  ${task.task}",
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(
                    brush = Brush.horizontalGradient(
                        0.0f to MaterialTheme.colors.background,
                        0.8f to MaterialTheme.colors.secondaryVariant.copy(alpha = 0.6f),
                        1.0f to MaterialTheme.colors.secondary
                    ), shape = CircleShape
                )
                .padding(8.dp),
            textAlign = TextAlign.Justify
        )

        Checkbox(
            checked = task.isChecked.value,
            modifier = Modifier
                .weight(0.1f),
            onCheckedChange = { task.isChecked.value = task.isChecked.value.not() })

        if (task.isChecked.value)
            Box(modifier = Modifier.weight(0.1f)) {
                CustomIconButton(
                    painter = painterResource(id = R.drawable.ic_delete_24),
                    description = "Button for delete task"
                ) {
                    onClickDelete(task)
                }
            }
    }

}