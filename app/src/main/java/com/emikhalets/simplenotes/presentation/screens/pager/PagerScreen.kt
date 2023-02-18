package com.emikhalets.simplenotes.presentation.screens.pager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.emikhalets.simplenotes.R
import com.emikhalets.simplenotes.domain.entities.TopBarActionEntity
import com.emikhalets.simplenotes.presentation.core.AppTopBar
import com.emikhalets.simplenotes.presentation.screens.notes_list.NotesListScreen
import com.emikhalets.simplenotes.presentation.screens.tasks_list.TasksListScreen
import com.emikhalets.simplenotes.presentation.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScreen(
    viewModel: PagerViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    var topBarTitle by remember { mutableStateOf("") }
    var checkedTasksVisible by remember { mutableStateOf(true) }
    var tasksTabSelected by remember { mutableStateOf(false) }

    val pages = listOf(
        stringResource(R.string.pager_title_tasks),
        stringResource(R.string.pager_title_notes)
    )

    val checkedTasksIcon = if (checkedTasksVisible) Icons.Default.VisibilityOff
    else Icons.Default.Visibility

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.dataStore.collectCheckedTasksVisible { checkedTasksVisible = it }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = topBarTitle,
            actions = if (tasksTabSelected) {
                listOf(
                    TopBarActionEntity(checkedTasksIcon) {
                        scope.launch { viewModel.dataStore.changeCheckedTasksVisible() }
                    }
                )
            } else emptyList()
        )
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                topBarTitle = pages[pagerState.currentPage]
                tasksTabSelected = pagerState.currentPage == 0
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            }
        ) {
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.scrollToPage(index) } },
                )
            }
        }
        HorizontalPager(count = 2, state = pagerState) { page ->
            when (page) {
                0 -> TasksListScreen(checkedTasksVisible = checkedTasksVisible)
                1 -> NotesListScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    AppTheme {
        PagerScreen()
    }
}
