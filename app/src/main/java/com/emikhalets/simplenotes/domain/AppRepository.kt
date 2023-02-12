package com.emikhalets.simplenotes.domain

import com.emikhalets.simplenotes.domain.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getTasks(): Result<Flow<List<TaskEntity>>>
    fun getTask(id: Long): Result<Flow<TaskEntity>>
    fun insertTask(entity: TaskEntity): Result<Unit>
    fun updateTask(entity: TaskEntity): Result<Unit>
    fun deleteTask(entity: TaskEntity): Result<Unit>
}