package com.emikhalets.simplenotes.data.mappers

import com.emikhalets.simplenotes.data.database.entities.NoteDb
import com.emikhalets.simplenotes.domain.entities.NoteEntity
import javax.inject.Inject

class NotesMapper @Inject constructor() {

    fun mapDbToEntity(dbEntity: NoteDb): NoteEntity = NoteEntity(
        id = dbEntity.id,
        title = dbEntity.title,
        content = dbEntity.content,
        sortOrder = dbEntity.sortOrder,
        saveTime = dbEntity.saveTime,
    )

    fun mapDbListToEntityList(dbList: List<NoteDb>): List<NoteEntity> = dbList.map {
        mapDbToEntity(it)
    }

    fun mapEntityToDb(entity: NoteEntity): NoteDb = NoteDb(
        id = entity.id,
        title = entity.title,
        content = entity.content,
        sortOrder = entity.sortOrder,
        saveTime = entity.saveTime,
    )

    fun mapEntityListToDbList(entityList: List<NoteEntity>): List<NoteDb> = entityList.map {
        mapEntityToDb(it)
    }
}