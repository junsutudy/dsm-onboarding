package com.example.dsm2024

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dsm2024.data.VisitorRepository
import com.example.dsm2024.ui.home.HomeScreen
import com.example.dsm2024.ui.theme.DSM2024Theme
import com.example.dsm2024.ui.visitor.Comment
import com.example.dsm2024.ui.visitor.VisitorLogsScreen
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {

    private val sharedPref by lazy { getPreferences(Context.MODE_PRIVATE) }
    private fun getAllVisitorLogs() = sharedPref.all.map { value ->
        Comment(
            date = LocalDateTime.parse(value.key),
            value = value.value.toString(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DSM2024Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val list = remember { getAllVisitorLogs().toMutableList() }
                    `개쩌는 화면`(
                        onWriteVisitorLog = { comment: Comment ->
                            with(sharedPref.edit()) {
                                putString(comment.date.toString(), comment.value)
                                apply()
                            }
                            list.add(comment)
                            VisitorRepository.writeVisitorLog(message = comment.value)
                        },
                        visitorLogs = list,
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun `개쩌는 화면`(
    onWriteVisitorLog: (Comment) -> Unit,
    visitorLogs: List<Comment>,
) {

    val (currentSection, onChangeCurrentSection) = remember {
        mutableStateOf(MainSections.HOME)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                MainSections.entries.forEach { section ->
                    val selected = section == currentSection
                    NavigationBarItem(
                        selected = selected,
                        onClick = { onChangeCurrentSection(section) },
                        icon = {
                            Icon(
                                imageVector = if (selected) {
                                    section.icons.selectedIcon
                                } else {
                                    section.icons.defaultIcon
                                },
                                contentDescription = section.label,
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            when (currentSection) {
                MainSections.HOME -> HomeScreen()
                MainSections.VISITOR_LOGS -> VisitorLogsScreen(
                    onWriteVisitorLog = onWriteVisitorLog,
                    visitorLogs = visitorLogs,
                )
            }
        }
    }
}

enum class MainSections(
    val icons: Icon,
    val label: String,
) {
    HOME(
        icons = Icon(
            selectedIcon = Icons.Filled.Home,
            defaultIcon = Icons.Outlined.Home,
        ),
        label = "홈",
    ),
    VISITOR_LOGS(
        icons = Icon(
            selectedIcon = Icons.Filled.Edit,
            defaultIcon = Icons.Outlined.Edit,
        ),
        label = "방문록",
    ),
}

class Icon(
    val defaultIcon: ImageVector,
    val selectedIcon: ImageVector,
)
