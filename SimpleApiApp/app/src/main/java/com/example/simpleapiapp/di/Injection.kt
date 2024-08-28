package com.example.simpleapiapp.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleapiapp.data.repository.PostRepository
import com.example.simpleapiapp.ui.PostViewModel
import com.example.simpleapiapp.data.database.AppDatabase
import com.example.simpleapiapp.data.network.ApiService

object Injection {
    private fun provideApiService(): ApiService {
        return ApiService.create()
    }

    private fun providePostRepository(context: Context): PostRepository {
        val database = AppDatabase.getDatabase(context)
        return PostRepository(database.postDao(), provideApiService())
    }

    fun providePostViewModelFactory(context: Context): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
                    return PostViewModel(providePostRepository(context)) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}
