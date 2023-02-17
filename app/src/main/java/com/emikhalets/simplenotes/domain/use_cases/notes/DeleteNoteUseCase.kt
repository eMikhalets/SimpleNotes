package com.emikhalets.simplenotes.domain.use_cases.notes

import com.emikhalets.simplenotes.domain.AppRepository
import com.emikhalets.simplenotes.domain.entities.NoteEntity
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(entity: NoteEntity) = repository.deleteNote(entity)
}