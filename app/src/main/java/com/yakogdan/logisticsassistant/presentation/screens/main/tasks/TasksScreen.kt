package com.yakogdan.logisticsassistant.presentation.screens.main.tasks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TasksScreen() {
    val tabItems = listOf("Входящие", "В работе")
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    val pagerState = rememberPagerState {
        tabItems.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Задания",
            fontSize = 22.sp,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
        )
        TabRow(
            modifier = Modifier.padding(horizontal = 16.dp),
            selectedTabIndex = selectedTabIndex,
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabItems.forEachIndexed { index, name ->
                Tab(
                    selectedContentColor = MaterialTheme.colorScheme.onBackground,
                    unselectedContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = name, fontSize = 16.sp) }
                )
            }
        }
        val task = TaskModel(
            taskNumber = 1,
            cost = 30000,
            taskDate = "11.08.2023 12:00",
            status = TaskStatus.New,
            route = RouteModel(
                addressFrom = "Машиностроительная улица, 91",
                dateFrom = "12.08.2023 12:00",
                addressTo = "Магистральная улица, 52",
                dateTo = "13.08.2023 13:00"
            )
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(MaterialTheme.colorScheme.surface)
        ) { index ->

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(modifier = Modifier.padding(10.dp)) {
                    val list = listOf("1", "2", "3")
                    items(list) {
                        TaskItem(task)
                    }
                }
            }
        }
    }
}