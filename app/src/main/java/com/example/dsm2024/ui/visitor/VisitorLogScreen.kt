package com.example.dsm2024.ui.visitor

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dsm2024.R
import java.time.LocalDateTime


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisitorLogScreen(
    onWriteVisitorLog: (Comment) -> Unit,
    visitorLogs: List<Comment>,
) {
    val (comment, onChangeComment) = remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .imePadding(),
        topBar = { TopAppBar(title = { Text(text = "방명록") }) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn(
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
            text = comment.date.toString(),
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
