package com.books.app.data

data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val summary: String,
    val genre: String,
    val cover_url: String,
    val views: String,
    val likes: String,
    val quotes: String
)

data class BookResponse(
    val books: List<Book>,
    val top_banner_slides: List<Banner>,
    val you_will_like_section: List<Int>
)

data class Banner(
    val id: Int,
    val book_id: Int,
    val cover: String
)

data class JsonObject(
    val books: List<Book>,
    val you_will_like_section: List<Int>,
)