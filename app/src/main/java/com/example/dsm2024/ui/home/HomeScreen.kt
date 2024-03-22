package com.example.dsm2024.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dsm2024.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val images = listOf(
        R.drawable.img_main_landscape,
        R.drawable.img_main_cafeteria_floor_1,
        R.drawable.img_main_dsm_field_view,
        R.drawable.img_main_lecture,
        R.drawable.img_main_cafeteria_floor_1_2,
        R.drawable.img_main_introduction_of_company,
        R.drawable.img_main_exercising,
        R.drawable.img_main_king_of_daejeon_education,
        R.drawable.img_main_jobis_members,
        R.drawable.img_main_the_legend_three_2,
        R.drawable.img_main_the_legend_three,
        R.drawable.img_main_dms_star_gogo,
        R.drawable.img_main_vice_president,
        R.drawable.img_main_walkhub,
    )
    val pagerState = rememberPagerState { images.size }

    LaunchedEffect(Unit) {
        scope.launch {
            while (true) {
                delay(2000L)
                pagerState.animateScrollToPage(
                    if (pagerState.currentPage == pagerState.pageCount - 1) {
                        0
                    } else {
                        pagerState.currentPage + 1
                    },
                )
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Hello, DSM!") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(
                            painter = painterResource(id = R.drawable.img_dsm),
                            contentDescription = "DSM 로고",
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 16.dp,
                ),
                pageSpacing = 8.dp,
                userScrollEnabled = false,
            ) { pageIndex ->
                ElevatedCard(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()
                        .height(256.dp),
                    shape = MaterialTheme.shapes.large,
                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painterResource(id = images[pageIndex]),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    progress = (pagerState.currentPage + 1) / images.size.toFloat(),
                )
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = if (pagerState.currentPage == 0) {
                                    pagerState.pageCount
                                } else {
                                    pagerState.currentPage - 1
                                },
                            )
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                    )
                }
                IconButton(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(
                                page = if (pagerState.currentPage == pagerState.pageCount - 1) {
                                    0
                                } else {
                                    pagerState.currentPage + 1
                                },
                            )
                        }
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Junsu Park",
                        title = "JunJaBoy",
                        content = "주접충",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/JunJaBoy"),
                                ),
                            )
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        ),
                    )

                    `개쩌는 카드`(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        head = "Jungho Lee",
                        title = "jeongho1209",
                        content = "학생회장",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/jeongho1209"),
                                ),
                            )
                        },
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Haeun Choi",
                        title = "chlgkdms",
                        content = "개굴?",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/chlgkdms"),
                                ),
                            )
                        },
                    )

                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Yeonwoo Kim",
                        title = "yeon0821",
                        content = "변절자",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/yeon0821"),
                                ),
                            )
                        },
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Seunghoon Jung",
                        title = "Tmdhoon2",
                        content = "똑똑한 청년.",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/Tmdhoon2"),
                                ),
                            )
                        },
                    )
                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Kanghyuk Lee",
                        title = "gurdl0525",
                        content = "백엔드 해라",
                        onClick = {
                            context.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://github.com/gurdl0525"),
                                ),
                            )
                        },
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun `개쩌는 카드`(
    modifier: Modifier = Modifier,
    head: String,
    title: String,
    content: String,
    onClick: () -> Unit = {},
    colors: CardColors = CardDefaults.cardColors(),
) {
    Card(
        modifier = modifier,
        onClick = onClick,
        colors = colors,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = head,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = content,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}
