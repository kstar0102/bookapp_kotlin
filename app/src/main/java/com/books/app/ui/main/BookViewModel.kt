package com.books.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.Banner
import com.books.app.data.Book
import com.books.app.util.loadJsonFromAsset
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    private val _booksByGenre = MutableStateFlow<Map<String, List<Book>>>(emptyMap())
    val booksByGenre: StateFlow<Map<String, List<Book>>> = _booksByGenre

    private val _banners = MutableStateFlow<List<Banner>>(emptyList())
    val banners: StateFlow<List<Banner>> = _banners

    private val _books = MutableStateFlow<List<Book>>(emptyList())

    init {
        fetchBooksAndBanners()
    }

    private fun fetchBooksAndBanners() {
        viewModelScope.launch {
            val context = getApplication<Application>().applicationContext
            val bookResponse = loadJsonFromAsset(context, "books_data.json")
            _books.value = bookResponse?.books ?: emptyList()
            val groupedBooks = bookResponse?.books?.groupBy { it.genre } ?: emptyMap()
            _booksByGenre.value = groupedBooks
            _banners.value = bookResponse?.top_banner_slides ?: emptyList()
        }
    }

    fun getBookById(bookId: Int): Book? {
        return _books.value.find { it.id == bookId }
    }
}