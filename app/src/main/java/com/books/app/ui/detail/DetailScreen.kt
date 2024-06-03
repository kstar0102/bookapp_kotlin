package com.books.app.ui.detail

import androidx.compose.runtime.Composable
import com.books.app.data.Book
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.books.app.R
import com.books.app.viewmodel.DetailViewModel
import com.books.app.theme.Typography
import com.books.app.theme.detailScreenBackgroundColor
import com.books.app.util.VerticalSpacer

@Composable
fun DetailScreen(book: Book, navController: NavController, detailViewModel: DetailViewModel = viewModel()) {
    val youWillAlsoLikeBooks by detailViewModel.youWillAlsoLike.collectAsState()

    val customColor = detailScreenBackgroundColor
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(customColor)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 35.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(250.dp) // Set the width of the Surface
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = book.cover_url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = book.name,
                    style = Typography.h6.copy(
                        color = Color.White
                    )
                )
                Text(
                    text = book.author,
                    style = Typography.body2.copy(
                        color = Color.Gray
                    )
                )
            }
        }
        VerticalSpacer(20)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = book.views,
                            style = Typography.h6.copy(
                                color = Color.Black
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.readers),
                            style = Typography.body2.copy(
                                color = Color.Gray
                            )
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = book.likes,
                            style = Typography.h6.copy(
                                color = Color.Black
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.likes),
                            style = Typography.body2.copy(
                                color = Color.Gray
                            )
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = book.quotes,
                            style = Typography.h6.copy(
                                color = Color.Black
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.quotes),
                            style = Typography.body2.copy(
                                color = Color.Gray
                            )
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = book.genre,
                            style = Typography.h6.copy(
                                color = Color.Black
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.genre),
                            style = Typography.body2.copy(
                                color = Color.Gray
                            )
                        )
                    }
                }

                VerticalSpacer(16)
                Divider(color = Color.Gray, thickness = 0.8.dp, )
                VerticalSpacer(16)

                Text(
                    text = stringResource(id = R.string.summary),
                    style = Typography.h6.copy(
                        color = Color.Black
                    )
                )
                VerticalSpacer(10)

                Text(
                    text = book.summary,
                    style = Typography.body2.copy(
                        color = Color.Black
                    )
                )

                VerticalSpacer(16)
                Divider(color = Color.Gray, thickness = 0.8.dp, )
                VerticalSpacer(16)

                Text(
                    text = stringResource(id = R.string.youwillalsolike),
                    style = Typography.h6.copy(
                        color = Color.Black
                    )
                )
                VerticalSpacer(10)

                DetailRow(
                    bookIds = youWillAlsoLikeBooks.map { it.id },
                    navController = navController,
                    detailViewModel = detailViewModel
                )

                VerticalSpacer(16)

                Button(
                    onClick = { /* TODO: Handle Read Now */ },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDD48A1)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .width(350.dp)
                        .height(50.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.readnow),
                        style = Typography.h6.copy(
                            color = Color.White
                        ),
                    )
                }
            }
        }
    }
}