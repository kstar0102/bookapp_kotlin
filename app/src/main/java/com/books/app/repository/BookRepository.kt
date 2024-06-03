package com.books.app.repository

import android.util.Log
import com.books.app.data.Book
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class BookRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getBooks(): List<Book> {
        return try {
            val snapshot = firestore.collection("books").get().await()
            snapshot.documents.mapNotNull { it.toObject(Book::class.java) }
        } catch (e: Exception) {
            Log.e("BookRepository", "Error getting books", e)
            emptyList()
        }
    }
}
