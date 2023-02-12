package com.emikhalets.simplenotes.presentation.screens.tasks_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emikhalets.simplenotes.R
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import com.emikhalets.simplenotes.presentation.core.AppTopBar
import com.emikhalets.simplenotes.presentation.theme.AppTheme
import com.emikhalets.simplenotes.utils.toast

@Composable
fun TasksListScreen(
    navigateToTaskItem: (Long) -> Unit,
    viewModel: TasksListViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getAllTasks()
    }

    LaunchedEffect(state.error) {
        val error = state.error
        if (error != null) {
            val message = error.asString(context)
            message.toast(context)
            viewModel.resetError()
        }
    }

    TasksListScreen(
        tasksList = state.tasksList,
        onTaskClick = { id -> navigateToTaskItem(id) },
        onCheckTask = { entity -> viewModel.updateTask(entity) },
    )
}

@Composable
private fun TasksListScreen(
    tasksList: List<TaskEntity>,
    onTaskClick: (Long) -> Unit,
    onCheckTask: (TaskEntity) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(title = stringResource(id = R.string.tasks_list_screen_title))

        LazyColumn(Modifier.fillMaxWidth()) {
            items(tasksList) { entity ->
                TaskRow(entity, onTaskClick, onCheckTask)
            }
        }
    }
}

@Composable
private fun TaskRow(
    entity: TaskEntity,
    onTaskClick: (Long) -> Unit,
    onCheckTask: (TaskEntity) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick(entity.id) }
            .padding(8.dp)
    ) {
        Checkbox(
            checked = entity.checked,
            onCheckedChange = { onCheckTask(entity) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = entity.content,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    AppTheme {
        TasksListScreen(
            tasksList = listOf(
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
            ),
            onTaskClick = {},
            onCheckTask = {},
        )
    }
}
