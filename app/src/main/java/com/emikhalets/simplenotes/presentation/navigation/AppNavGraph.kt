package com.emikhalets.simplenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.emikhalets.simplenotes.presentation.screens.task_item.TasksItemScreen
import com.emikhalets.simplenotes.presentation.screens.tasks_list.TasksListScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, AppScreen.TasksList.route) {

        composable(AppScreen.TasksList.route) {
            TasksListScreen(
                navigateToTaskItem = { navController.navigate(AppScreen.TasksItem.route) },
            )
        }

        composable(
            route = "${AppScreen.TasksItem.route}/{${NavArgs.TASK_ID}}",
            arguments = listOf(navArgument(NavArgs.TASK_ID) { type = NavType.LongType })
        ) {
            TasksItemScreen(
                taskId = it.arguments?.getLong(NavArgs.TASK_ID) ?: 0,
                navigateBack = { navController.popBackStack() }
            )
        }

    }
}