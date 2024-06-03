package com.books.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStreamReader
import com.books.app.data.JsonObject

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val _books = MutableStateFlow<List<Book>>(emptyList())
    val books: StateFlow<List<Book>> = _books

    private val _youWillAlsoLike = MutableStateFlow<List<Book>>(emptyList())
    val youWillAlsoLike: StateFlow<List<Book>> = _youWillAlsoLike

    init {
        loadBooks()
    }

    private fun loadBooks() {
        viewModelScope.launch {
            try {
                val context = getApplication<Application>().applicationContext
                val inputStream = context.assets.open("books_data.json")
                val reader = InputStreamReader(inputStream)
                val jsonType = object : TypeToken<JsonObject>() {}.type
                val jsonObject: JsonObject = Gson().fromJson(reader, jsonType)

                val loadedBooks = jsonObject.books
                val youWillLikeIds = jsonObject.you_will_like_section

                _books.value = loadedBooks

                val youWillAlsoLikeBooks = youWillLikeIds.mapNotNull { id ->
                    loadedBooks.find { it.id == id }
                }
                _youWillAlsoLike.value = youWillAlsoLikeBooks
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getBookById(id: Int): Book? {
        return _books.value.find { it.id == id }
    }
}
