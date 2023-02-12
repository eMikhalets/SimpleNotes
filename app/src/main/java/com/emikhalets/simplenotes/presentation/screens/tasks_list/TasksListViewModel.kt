package com.emikhalets.simplenotes.presentation.screens.tasks_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import com.emikhalets.simplenotes.domain.use_cases.tasks.DeleteTaskUseCase
import com.emikhalets.simplenotes.domain.use_cases.tasks.GetAllTasksUseCase
import com.emikhalets.simplenotes.domain.use_cases.tasks.InsertTaskUseCase
import com.emikhalets.simplenotes.domain.use_cases.tasks.UpdateTaskUseCase
import com.emikhalets.simplenotes.utils.UiString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val insertTaskUseCase: InsertTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(TasksListState())
    val state get() = _state.asStateFlow()

    fun resetError() = _state.update { it.copy(error = null) }

    fun getAllTasks() {
        viewModelScope.launch {
            getAllTasksUseCase.invoke()
                .onSuccess { flow -> setAllTasksState(flow) }
                .onFailure { throwable -> handleFailure(throwable) }
        }
    }

    private suspend fun setAllTasksState(flow: Flow<List<TaskEntity>>) {
        flow.collectLatest { list ->
            _state.update { it.copy(tasksList = list) }
        }
    }

    fun insertTask(content: String) {
        viewModelScope.launch {
            val entity = TaskEntity(content = content)
            insertTaskUseCase.invoke(entity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    fun updateTask(entity: TaskEntity?, newContent: String) {
        entity ?: return
        viewModelScope.launch {
            val newEntity = entity.copy(content = newContent)
            updateTaskUseCase.invoke(newEntity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    fun updateTask(entity: TaskEntity, newChecked: Boolean) {
        viewModelScope.launch {
            val newEntity = entity.copy(checked = newChecked)
            updateTaskUseCase.invoke(newEntity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    fun deleteTask(entity: TaskEntity) {
        viewModelScope.launch {
            deleteTaskUseCase.invoke(entity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    private fun handleFailure(throwable: Throwable) {
        _state.update { it.copy(error = UiString.create(throwable.message)) }
    }
}
