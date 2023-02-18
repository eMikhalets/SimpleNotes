package com.emikhalets.simplenotes.presentation.screens.notes_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emikhalets.simplenotes.R
import com.emikhalets.simplenotes.domain.entities.NoteEntity
import com.emikhalets.simplenotes.presentation.core.AppTopBar
import com.emikhalets.simplenotes.presentation.theme.AppTheme
import com.emikhalets.simplenotes.utils.toast

@Composable
fun NotesListScreen(viewModel: NotesListViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsState()

    var editNoteEntity by remember { mutableStateOf<NoteEntity?>(null) }
    var showAddTaskDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getAllNotes()
    }

    LaunchedEffect(state.error) {
        val error = state.error
        if (error != null) {
            val message = error.asString(context)
            message.toast(context)
            viewModel.resetError()
        }
    }

    NotesListScreen(
        notesList = state.notesList,
        onNoteClick = { entity -> editNoteEntity = entity },
        onAddNoteClick = { showAddTaskDialog = true },
    )

    if (showAddTaskDialog) {
        AddNoteDialog(
            onDismiss = { showAddTaskDialog = false },
            onSaveClick = { title, content ->
                viewModel.insertNote(title, content)
                showAddTaskDialog = false
            }
        )
    }

    if (editNoteEntity != null) {
        EditNoteDialog(
            initTitle = editNoteEntity?.title ?: "",
            initContent = editNoteEntity?.content ?: "",
            onDismiss = { editNoteEntity = null },
            onSaveClick = { title, content ->
                viewModel.updateNote(editNoteEntity, title, content)
                editNoteEntity = null
            }
        )
    }
}

@Composable
private fun NotesListScreen(
    notesList: List<NoteEntity>,
    onNoteClick: (NoteEntity) -> Unit,
    onAddNoteClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            AppTopBar(title = stringResource(id = R.string.notes_list_screen_title))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(notesList) { entity ->
                    NoteRow(entity, onNoteClick)
                }
            }
        }
        FloatingActionButton(
            onClick = { onAddNoteClick() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
}

@Composable
private fun NoteRow(
    entity: NoteEntity,
    onNoteClick: (NoteEntity) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoteClick(entity) }
            .padding(8.dp, 4.dp)
    ) {
        Text(
            text = entity.title,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = entity.content,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    AppTheme {
        NotesListScreen(
            notesList = listOf(
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
                NoteEntity(title = "Note title", content = "Note content"),
            ),
            onNoteClick = {},
            onAddNoteClick = {},
        )
    }
}
