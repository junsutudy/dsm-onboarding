package com.example.dsm2024.ui.visitor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorLogsScreen(
    onWriteVisitorLog: (Comment) -> Unit,
    visitorLogs: List<Comment>,
) {
    val scope = rememberCoroutineScope()
    val (comment, onChangeComment) = remember { mutableStateOf("") }

    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
            .fillMaxSize()
            .imePadding(),
        topBar = {
            LargeTopAppBar(
                title = { Text(text = "방명록") },
                colors = TopAppBarDefaults.largeTopAppBarColors(),
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            val state = rememberLazyListState()
            LazyColumn(
                state = state,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(visitorLogs) { comment ->
                    Comment(
                        modifier = Modifier.fillMaxWidth(),
                        comment = comment,
                    )
                }
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = comment,
                onValueChange = onChangeComment,
                label = { Text(text = "댓글을 입력해주세요") },
                trailingIcon = if (comment.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = {
                                onWriteVisitorLog(
                                    Comment(
                                        value = comment,
                                        date = LocalDateTime.now(),
                                    ),
                                )
                                onChangeComment("")
                                scope.launch { state.scrollToItem(index = visitorLogs.lastIndex) }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                            )
                        }
                    }
                } else null,
            )
        }
    }
}

@Composable
private fun Comment(
    modifier: Modifier = Modifier,
    comment: Comment,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 8.dp,
                end = 8.dp,
            ),
            text = comment.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")),
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp,
            ),
            text = comment.value,
        )
    }
}

data class Comment(
    val value: String,
    val date: LocalDateTime,
)
