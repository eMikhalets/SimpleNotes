package com.emikhalets.simplenotes.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emikhalets.simplenotes.presentation.screens.pager.PagerScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, AppScreen.Pager.route) {

        composable(AppScreen.Pager.route) {
            PagerScreen()
        }

    }
}