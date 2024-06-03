package com.books.app.di

import com.books.app.viewmodel.BookViewModel
import com.books.app.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { BookViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
