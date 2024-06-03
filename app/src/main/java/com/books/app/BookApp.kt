package com.books.app

import android.app.Application
import com.google.firebase.FirebaseApp

class BookApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}
