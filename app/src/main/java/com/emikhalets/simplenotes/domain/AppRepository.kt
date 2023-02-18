package com.emikhalets.simplenotes.domain

import com.emikhalets.simplenotes.domain.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getTasks(): Result<Flow<List<TaskEntity>>>
    fun getTask(id: Long): Result<Flow<TaskEntity>>
    suspend fun insertTask(entity: TaskEntity): Result<Unit>
    suspend fun updateTask(entity: TaskEntity): Result<Unit>
    suspend fun updateTasks(entities: List<TaskEntity>): Result<Unit>
    suspend fun deleteTask(entity: TaskEntity): Result<Unit>
}