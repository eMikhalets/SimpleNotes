package com.emikhalets.simplenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emikhalets.simplenotes.presentation.screens.notes_list.TasksListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, AppScreen.TasksList.route) {

        composable(AppScreen.TasksList.route) {
            TasksListScreen()
        }

    }
}