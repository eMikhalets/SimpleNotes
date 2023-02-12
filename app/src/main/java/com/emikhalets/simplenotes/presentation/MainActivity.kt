package com.emikhalets.simplenotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.rememberNavController
import com.emikhalets.simplenotes.presentation.core.AppScaffold
import com.emikhalets.simplenotes.presentation.navigation.AppNavGraph
import com.emikhalets.simplenotes.presentation.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()

            AppTheme {
                AppScaffold(navController, scaffoldState) {
                    AppNavGraph(navController)
                }
            }
        }
    }
}