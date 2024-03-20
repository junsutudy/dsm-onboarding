package com.example.dsm2024.ui.home

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
import androidx.compose.material3.Card
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dsm2024.R

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
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
            val images = listOf(
                R.drawable.img_main_landscape,
                R.drawable.img_main_cafeteria_floor_1,
                R.drawable.img_main_lecture,
                R.drawable.img_main_cafeteria_floor_1_2,
                R.drawable.img_main_jobis_members,
                R.drawable.img_main_dms_star_gogo,
            )

            val pagerState = rememberPagerState { images.size }
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                contentPadding = PaddingValues(
                    vertical = 8.dp,
                    horizontal = 16.dp,
                ),
                pageSpacing = 8.dp,
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
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                LinearProgressIndicator(progress = (pagerState.currentPage + 1) / images.size.toFloat())
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
                        head = "Github",
                        title = "JunJaBoy",
                        content = "주접충",
                    )

                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Github",
                        title = "Tmdhoon2",
                        content = "똑똑한 청년.",
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
                        head = "Github",
                        title = "chlgkdms",
                        content = "개굴?",
                    )

                    `개쩌는 카드`(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        head = "Github",
                        title = "yeon0821",
                        content = "변절자",
                    )
                }
            }
        }
    }
}

@Composable
private fun `개쩌는 카드`(
    modifier: Modifier = Modifier,
    head: String,
    title: String,
    content: String,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = head,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = title,
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
