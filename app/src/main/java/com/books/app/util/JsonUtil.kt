package com.books.app.util

import android.content.Context
import com.books.app.data.BookResponse
import com.google.gson.Gson
import java.io.InputStreamReader

fun loadJsonFromAsset(context: Context, fileName: String): BookResponse? {
    return try {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        Gson().fromJson(reader, BookResponse::class.java)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}