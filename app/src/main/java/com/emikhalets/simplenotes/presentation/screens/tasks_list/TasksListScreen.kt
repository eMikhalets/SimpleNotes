package com.emikhalets.simplenotes.presentation.screens.tasks_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emikhalets.simplenotes.R
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import com.emikhalets.simplenotes.domain.entities.TopBarActionEntity
import com.emikhalets.simplenotes.presentation.core.AppTopBar
import com.emikhalets.simplenotes.presentation.theme.AppTheme
import com.emikhalets.simplenotes.utils.toast
import kotlinx.coroutines.launch

@Composable
fun TasksListScreen(viewModel: TasksListViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()
    val scope = rememberCoroutineScope()

    var editTaskEntity by remember { mutableStateOf<TaskEntity?>(null) }
    var showAddTaskDialog by remember { mutableStateOf(false) }
    var checkedTasksVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.dataStore.collectCheckedTasksVisible { checkedTasksVisible = it }
        }
    }

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
        checkedTasksVisible = checkedTasksVisible,
        onTaskClick = { entity -> editTaskEntity = entity },
        onCheckTask = { entity, checked -> viewModel.updateTask(entity, checked) },
        onAddTaskClick = { showAddTaskDialog = true },
        onCheckedTaskIconClick = {
            scope.launch { viewModel.dataStore.changeCheckedTasksVisible() }
        },
    )

    if (showAddTaskDialog) {
        AddTaskDialog(
            onDismiss = { showAddTaskDialog = false },
            onSaveClick = { taskContent ->
                viewModel.insertTask(taskContent)
                showAddTaskDialog = false
            }
        )
    }

    if (editTaskEntity != null) {
        EditTaskDialog(
            initContent = editTaskEntity?.content ?: "",
            onDismiss = { editTaskEntity = null },
            onSaveClick = { taskContent ->
                viewModel.updateTask(editTaskEntity, taskContent)
                editTaskEntity = null
            }
        )
    }
}

@Composable
private fun TasksListScreen(
    tasksList: List<TaskEntity>,
    checkedTasksVisible: Boolean,
    onTaskClick: (TaskEntity) -> Unit,
    onCheckTask: (TaskEntity, Boolean) -> Unit,
    onAddTaskClick: () -> Unit,
    onCheckedTaskIconClick: () -> Unit,
) {
    val checkedTasksIcon = if (checkedTasksVisible) Icons.Default.VisibilityOff
    else Icons.Default.Visibility

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = stringResource(id = R.string.tasks_list_screen_title),
            actions = listOf(
                TopBarActionEntity(checkedTasksIcon) { onCheckedTaskIconClick() }
            )
        )

        Box(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.fillMaxWidth()) {
                items(tasksList) { entity ->
                    TaskRow(entity, onTaskClick, onCheckTask)
                }
            }
            FloatingActionButton(
                onClick = { onAddTaskClick() },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
private fun TaskRow(
    entity: TaskEntity,
    onTaskClick: (TaskEntity) -> Unit,
    onCheckTask: (TaskEntity, Boolean) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onTaskClick(entity) }
            .padding(8.dp)
    ) {
        Checkbox(
            checked = entity.checked,
            onCheckedChange = { onCheckTask(entity, it) }
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = entity.content,
            color = if (entity.checked) Color.Gray else Color.Unspecified,
            textDecoration = if (entity.checked) TextDecoration.LineThrough else null,
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
                TaskEntity(content = "Task content", checked = true),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
                TaskEntity(content = "Task content"),
            ),
            checkedTasksVisible = true,
            onTaskClick = {},
            onCheckTask = { _, _ -> },
            onAddTaskClick = {},
            onCheckedTaskIconClick = {},
        )
    }
}
