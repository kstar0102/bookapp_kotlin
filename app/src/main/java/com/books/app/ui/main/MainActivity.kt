package com.books.app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.books.app.viewmodel.BookViewModel
import com.google.firebase.FirebaseApp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.books.app.ui.detail.DetailScreen
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import com.books.app.viewmodel.DetailViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val bookViewModel: BookViewModel = viewModel()
    val detailViewModel: DetailViewModel = viewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(navController, bookViewModel)
        }
        composable(
            route = "detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.IntType })
        ) { backStackEntry ->
            val bookId = backStackEntry.arguments?.getInt("bookId")
            val book = bookId?.let { bookViewModel.getBookById(it) }
            book?.let {
                DetailScreen(book = it, navController = navController, detailViewModel = detailViewModel)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController, bookViewModel: BookViewModel = viewModel()) {
    val booksByGenre by bookViewModel.booksByGenre.collectAsState()
    val banners by bookViewModel.banners.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentPadding = PaddingValues(16.dp),
    ) {
        item {
            TopBanner(banners, bookViewModel, navController)
        }
        booksByGenre.forEach { (genre, books) ->
            item {
                BookRow(genre, books, navController)
            }
        }
    }
}
