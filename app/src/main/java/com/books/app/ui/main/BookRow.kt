package com.books.app.ui.main

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
import com.books.app.theme.Typography

@Composable
fun BookRow(genre: String, books: List<Book>, navController: NavController) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = genre,
            style = Typography.h6.copy(
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp),
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(books) { book ->
                BookItem(book){
                    navController.navigate("detail/${book.id}")
                }
            }
        }
    }
}

@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .padding(vertical = 8.dp)
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
            style = Typography.body1,
            maxLines = 2,
            modifier = Modifier.padding(1.dp),
        )
    }
}