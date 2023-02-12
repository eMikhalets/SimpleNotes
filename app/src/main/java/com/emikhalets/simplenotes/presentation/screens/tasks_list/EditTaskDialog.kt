package com.emikhalets.simplenotes.presentation.screens.tasks_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.emikhalets.simplenotes.R
import com.emikhalets.simplenotes.presentation.theme.AppTheme

@Composable
fun EditTaskDialog(
    initContent: String,
    onDismiss: () -> Unit,
    onSaveClick: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester.Default }

    var taskContent by remember { mutableStateOf(initContent) }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
                .background(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = taskContent,
                    onValueChange = { taskContent = it },
                    modifier = Modifier.focusRequester(focusRequester)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { onSaveClick(taskContent) }) {
                    Text(text = stringResource(id = R.string.tasks_list_save))
                }
            }
        }
    }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    AppTheme {
        EditTaskDialog("test content", {}, {})
    }
}