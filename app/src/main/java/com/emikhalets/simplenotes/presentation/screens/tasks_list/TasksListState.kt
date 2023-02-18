package com.emikhalets.simplenotes.presentation.screens.tasks_list

import com.emikhalets.simplenotes.domain.entities.TaskEntity
import com.emikhalets.simplenotes.utils.UiString

data class TasksListState(
    val tasksList: List<TaskEntity> = emptyList(),
    val checkedList: List<TaskEntity> = emptyList(),
    val error: UiString? = null,
)
