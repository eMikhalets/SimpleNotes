package com.emikhalets.simplenotes.presentation.screens.notes_list

import com.emikhalets.simplenotes.domain.entities.NoteEntity
import com.emikhalets.simplenotes.utils.UiString

data class NotesListState(
    val notesList: List<NoteEntity> = emptyList(),
    val error: UiString? = null,
)
