package com.emikhalets.simplenotes.presentation.screens.pager

//import com.google.accompanist.pager.ExperimentalPagerApi
//import com.google.accompanist.pager.HorizontalPager
//import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.emikhalets.simplenotes.presentation.screens.tasks_list.TasksListScreen
import com.emikhalets.simplenotes.presentation.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun PagerScreen(
    viewModel: PagerViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()

    var topBarTitle by remember { mutableStateOf("") }
    var checkedTasksVisible by remember { mutableStateOf(true) }
    var tasksTabSelected by remember { mutableStateOf(false) }

    val pages = listOf(
        stringResource(R.string.pager_title_tasks),
        stringResource(R.string.pager_title_notes)
    )

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.dataStore.collectCheckedTasksVisible { checkedTasksVisible = it }
        }
    }

    PagerScreen(
        pages = pages,
        topBarTitle = topBarTitle,
        tasksTabSelected = tasksTabSelected,
        checkedTasksVisible = checkedTasksVisible,
        onCheckedTaskChange = { checkedTasksVisible = !checkedTasksVisible },
        onTobBarTitleChange = { topBarTitle = it },
        onTasksBarSelectedChange = { tasksTabSelected = it },
    )
}

@Composable
fun PagerScreen(
    pages: List<String>,
    topBarTitle: String,
    tasksTabSelected: Boolean,
    checkedTasksVisible: Boolean,
    onCheckedTaskChange: () -> Unit,
    onTobBarTitleChange: (String) -> Unit,
    onTasksBarSelectedChange: (Boolean) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var currentPage by remember { mutableStateOf(0) }

    val checkedTasksIcon = if (checkedTasksVisible) Icons.Default.VisibilityOff
    else Icons.Default.Visibility

    Column(modifier = Modifier.fillMaxSize()) {
        AppTopBar(
            title = topBarTitle,
            actions = if (tasksTabSelected) {
                listOf(
                    TopBarActionEntity(checkedTasksIcon) { onCheckedTaskChange() }
                )
            } else emptyList()
        )
//        TabRow(
//            selectedTabIndex = currentPage,
//            indicator = { tabPositions ->
//                onTobBarTitleChange(pages[currentPage])
//                onTasksBarSelectedChange(currentPage == 0)
//                TabRowDefaults.Indicator(
//                    Modifier.tabIndicatorOffset(tabPositions[currentPage])
//                )
//            }
//        ) {
//            pages.forEachIndexed { index, title ->
//                Tab(
//                    text = { Text(title) },
//                    selected = currentPage == index,
//                    onClick = { scope.launch { currentPage = index } },
//                )
//            }
//        }
//        LazyRow {
//            items(listOf(0, 1)) { page ->
//                when (page) {
//                    0 -> TasksListScreen(checkedTasksVisible = checkedTasksVisible)
//                    1 -> NotesListScreen()
//                }
//            }
//        }
        onTobBarTitleChange(pages[currentPage])
        onTasksBarSelectedChange(currentPage == 0)
        TasksListScreen(checkedTasksVisible = checkedTasksVisible)
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    AppTheme {
        PagerScreen()
    }
}
