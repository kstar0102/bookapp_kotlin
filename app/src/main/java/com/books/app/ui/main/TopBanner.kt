package com.books.app.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.books.app.R
import com.books.app.data.Banner
import com.books.app.viewmodel.BookViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.books.app.theme.Typography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBanner(banners: List<Banner>, viewModel: BookViewModel, navController: NavController) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    // Auto-scroll functionality
    LaunchedEffect(pagerState) {
        scope.launch {
            while (true) {
                delay(3000)
                var newPosition = (pagerState.currentPage + 1) % banners.size
                pagerState.animateScrollToPage(newPosition)
            }
        }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.library),
            style = Typography.h6,
        )

        HorizontalPager(
            count = banners.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp) // Adjust height as necessary
        ) { page ->
            val banner = banners[page]
            val book = viewModel.getBookById(banner.book_id)
            book?.let {
                BannerItem(it.cover_url) {
                    navController.navigate("detail/${it.id}")
                }
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun BannerItem(coverUrl: String, onClick: () -> Unit) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = coverUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clickable(onClick = onClick)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}