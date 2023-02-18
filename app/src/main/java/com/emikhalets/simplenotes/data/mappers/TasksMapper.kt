package com.emikhalets.simplenotes.data.mappers

import com.emikhalets.simplenotes.data.database.entities.TaskDb
import com.emikhalets.simplenotes.domain.entities.TaskEntity
import javax.inject.Inject

class TasksMapper @Inject constructor() {

    fun mapDbToEntity(dbEntity: TaskDb): TaskEntity = TaskEntity(
        id = dbEntity.id,
        content = dbEntity.content,
        checked = dbEntity.checked,
        savedTime = dbEntity.savedTime
    )

    fun mapDbListToEntityList(dbList: List<TaskDb>): List<TaskEntity> = dbList.map {
        mapDbToEntity(it)
    }

    fun mapEntityToDb(entity: TaskEntity): TaskDb = TaskDb(
        id = entity.id,
        content = entity.content,
        checked = entity.checked,
        savedTime = entity.savedTime
    )

    fun mapEntityListToDbList(entityList: List<TaskEntity>): List<TaskDb> = entityList.map {
        mapEntityToDb(it)
    }
}