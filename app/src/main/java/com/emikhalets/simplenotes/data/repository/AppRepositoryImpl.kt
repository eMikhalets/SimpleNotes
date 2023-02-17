package com.emikhalets.simplenotes.data.repository

import com.emikhalets.simplenotes.data.database.NotesDao
import com.emikhalets.simplenotes.data.database.TasksDao
import com.emikhalets.simplenotes.data.mappers.NotesMapper
import com.emikhalets.simplenotes.data.mappers.TasksMapper
import com.emikhalets.simplenotes.domain.AppRepository
import com.emikhalets.simplenotes.domain.entities.NoteEntity
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import com.emikhalets.simplenotes.utils.executeAsync
import com.emikhalets.simplenotes.utils.executeSync
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val tasksDao: TasksDao,
    private val notesDao: NotesDao,
    private val tasksMapper: TasksMapper,
    private val notesMapper: NotesMapper,
) : AppRepository {

    override fun getTasks(): Result<Flow<List<TaskEntity>>> {
        return executeSync { tasksDao.getAllFlow().map { tasksMapper.mapDbListToEntityList(it) } }
    }

    override fun getTask(id: Long): Result<Flow<TaskEntity>> {
        return executeSync { tasksDao.getItemFlow(id).map { tasksMapper.mapDbToEntity(it) } }
    }

    override suspend fun insertTask(entity: TaskEntity): Result<Unit> {
        return executeAsync { tasksDao.insert(tasksMapper.mapEntityToDb(entity)) }
    }

    override suspend fun updateTask(entity: TaskEntity): Result<Unit> {
        return executeAsync { tasksDao.update(tasksMapper.mapEntityToDb(entity)) }
    }

    override suspend fun deleteTask(entity: TaskEntity): Result<Unit> {
        return executeAsync { tasksDao.delete(tasksMapper.mapEntityToDb(entity)) }
    }

    override fun getNotes(): Result<Flow<List<NoteEntity>>> {
        return executeSync { notesDao.getAllFlow().map { notesMapper.mapDbListToEntityList(it) } }
    }

    override fun getNote(id: Long): Result<Flow<NoteEntity>> {
        return executeSync { notesDao.getItemFlow(id).map { notesMapper.mapDbToEntity(it) } }
    }

    override suspend fun insertNote(entity: NoteEntity): Result<Unit> {
        return executeAsync { notesDao.insert(notesMapper.mapEntityToDb(entity)) }
    }

    override suspend fun updateNote(entity: NoteEntity): Result<Unit> {
        return executeAsync { notesDao.update(notesMapper.mapEntityToDb(entity)) }
    }

    override suspend fun deleteNote(entity: NoteEntity): Result<Unit> {
        return executeAsync { notesDao.delete(notesMapper.mapEntityToDb(entity)) }
    }
}