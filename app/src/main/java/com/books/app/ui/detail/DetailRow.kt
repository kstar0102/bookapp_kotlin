package com.books.app.ui.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.books.app.data.Book
import com.books.app.viewmodel.DetailViewModel
import com.books.app.theme.Typography

@Composable
fun DetailRow(bookIds: List<Int>, navController: NavController, detailViewModel: DetailViewModel) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(bookIds) { bookId ->
            val book = detailViewModel.getBookById(bookId)
            book?.let {
                BookItem(book = it, onClick = {
                    navController.navigate("detail/${book.id}")
                })
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .clickable(onClick = onClick)
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .width(150.dp)
                .padding(1.dp)
        ) {
            AsyncImage(
                model = book.cover_url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = book.name,
            Modifier.padding(1.dp),
            style = Typography.body2.copy(
                color = Color.Black,
            ),
            maxLines = 2,
        )
    }
}
