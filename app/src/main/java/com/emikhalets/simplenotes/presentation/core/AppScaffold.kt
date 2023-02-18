package com.emikhalets.simplenotes.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emikhalets.simplenotes.presentation.theme.AppTheme

@Composable
fun AppScaffold(
    navController: NavHostController,
    scaffoldState: ScaffoldState,
    content: @Composable () -> Unit,
) {
    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        Surface(color = MaterialTheme.colors.surface) {
            Box(modifier = Modifier.padding(it)) {
                content()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    AppTheme {
        AppScaffold(
            navController = rememberNavController(),
            scaffoldState = rememberScaffoldState()
        ) {
            Text(text = "Test")
        }
    }
}