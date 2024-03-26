package com.example.dsm2024.ui.visitor

import android.widget.Toast
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
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
    val (comment, changeComment) = remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val (shouldShowConfirmCommentDialog, onShouldShowConfirmCommentDialogChange) = rememberSaveable {
        mutableStateOf(false)
    }

    if (shouldShowConfirmCommentDialog) {
        AlertDialog(
            onDismissRequest = { onShouldShowConfirmCommentDialogChange(false) },
            title = { Text(text = "방명록 등록") },
            text = { Text(text = "방명록을 등록하시겠어요? 한 번 등록하면 삭제할 수 없어요. 다른 사람을 존중하는 댓글을 작성해 주세요.") },
            confirmButton = {
                val context = LocalContext.current
                Button(
                    onClick = {
                        onWriteVisitorLog(
                            Comment(
                                value = comment,
                                date = LocalDateTime.now(),
                            ),
                        )
                        changeComment("")
                        scope.launch {
                            delay(300)
                            listState.animateScrollToItem(index = 0)
                        }
                        onShouldShowConfirmCommentDialogChange(false)
                        Toast.makeText(context, "방명록 등록했지롱~", Toast.LENGTH_SHORT).show()
                    },
                ) {
                    Text(text = "확인")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onShouldShowConfirmCommentDialogChange(false)
                    },
                ) {
                    Text(text = "취소")
                }
            }
        )
    }

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
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(visitorLogs) { comment ->
                    Comment(
                        modifier = Modifier.fillMaxWidth(),
                        comment = comment,
                        // "나는 ⌜안드로이드⌟ 다." "어어.. 이제야 눈을 뜬건가?"
                    )
                }
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = comment,
                onValueChange = changeComment,
                label = { Text(text = "댓글을 입력해주세요") },
                trailingIcon = if (comment.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = { onShouldShowConfirmCommentDialogChange(true) },
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
