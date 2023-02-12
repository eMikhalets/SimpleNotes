package com.emikhalets.simplenotes.presentation.core

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emikhalets.simplenotes.presentation.theme.AppTheme

@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    onNavigateBack: (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        elevation = 0.dp,
        navigationIcon = if (onNavigateBack == null) {
            null
        } else {
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { onNavigateBack() }
                        .padding(20.dp, 16.dp)

                )
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        AppTopBar(
            title = "Test text",
            onNavigateBack = {},
            modifier = Modifier.padding(8.dp)
        )
    }
}