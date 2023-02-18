package com.emikhalets.simplenotes.presentation.screens.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emikhalets.simplenotes.domain.entities.NoteEntity
import com.emikhalets.simplenotes.domain.use_cases.notes.DeleteNoteUseCase
import com.emikhalets.simplenotes.domain.use_cases.notes.GetAllNotesUseCase
import com.emikhalets.simplenotes.domain.use_cases.notes.InsertNoteUseCase
import com.emikhalets.simplenotes.domain.use_cases.notes.UpdateNoteUseCase
import com.emikhalets.simplenotes.utils.UiString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(NotesListState())
    val state get() = _state.asStateFlow()

    fun resetError() = _state.update { it.copy(error = null) }

    fun getAllNotes() {
        viewModelScope.launch {
            getAllNotesUseCase.invoke()
                .onSuccess { flow -> setAllNotesState(flow) }
                .onFailure { throwable -> handleFailure(throwable) }
        }
    }

    private suspend fun setAllNotesState(flow: Flow<List<NoteEntity>>) {
        flow.collectLatest { list ->
            _state.update { it.copy(notesList = list) }
        }
    }

    fun insertNote(title: String, content: String) {
        viewModelScope.launch {
            val entity = NoteEntity(title = title, content = content, savedTime = Date().time)
            insertNoteUseCase.invoke(entity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    fun updateNote(entity: NoteEntity?, newTitle: String, newContent: String) {
        entity ?: return
        viewModelScope.launch {
            val newEntity = entity.copy(title = newTitle, content = newContent)
            updateNoteUseCase.invoke(newEntity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    fun deleteNote(entity: NoteEntity) {
        viewModelScope.launch {
            deleteNoteUseCase.invoke(entity).onFailure { throwable -> handleFailure(throwable) }
        }
    }

    private fun handleFailure(throwable: Throwable) {
        _state.update { it.copy(error = UiString.create(throwable.message)) }
    }
}
