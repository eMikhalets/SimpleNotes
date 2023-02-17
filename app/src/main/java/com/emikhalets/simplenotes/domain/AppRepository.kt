package com.emikhalets.simplenotes.domain

import com.emikhalets.simplenotes.domain.entities.NoteEntity
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import kotlinx.coroutines.flow.Flow

interface AppRepository {

    fun getTasks(): Result<Flow<List<TaskEntity>>>
    fun getTask(id: Long): Result<Flow<TaskEntity>>
    suspend fun insertTask(entity: TaskEntity): Result<Unit>
    suspend fun updateTask(entity: TaskEntity): Result<Unit>
    suspend fun updateTasks(entities: List<TaskEntity>): Result<Unit>
    suspend fun deleteTask(entity: TaskEntity): Result<Unit>

    fun getNotes(): Result<Flow<List<NoteEntity>>>
    fun getNote(id: Long): Result<Flow<NoteEntity>>
    suspend fun insertNote(entity: NoteEntity): Result<Unit>
    suspend fun updateNote(entity: NoteEntity): Result<Unit>
    suspend fun deleteNote(entity: NoteEntity): Result<Unit>
}