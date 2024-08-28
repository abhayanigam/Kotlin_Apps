package com.example.simpleapiapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleapiapp.data.repository.PostRepository
import com.example.simpleapiapp.model.Post
import kotlinx.coroutines.launch

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    fun getAllPostsLiveData(): LiveData<List<Post>> {
        return repository.getAllPostsLiveData()
    }

    fun refreshPosts(onComplete: () -> Unit) {
        viewModelScope.launch {
            try {
                repository.refreshPosts()   // Fetch and save new data from API
            } finally {
                onComplete() // Notify completion
            }
        }
    }

    fun deletePostById(postId: Int) {
        viewModelScope.launch {
            repository.deletePostById(postId)
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch {
            repository.updatePost(post)
        }
    }

    fun addSampleData() {
        viewModelScope.launch {
            val samplePosts = listOf(
                Post(id = 1, userId = 10, title = "Sample Post 10", body = "This is the body of Sample Post 1."),
                Post(id = 2, userId = 20, title = "Sample Post 20", body = "This is the body of Sample Post 2."),
                Post(id = 3, userId = 30, title = "Sample Post 30", body = "This is the body of Sample Post 3.")
            )
            repository.insertPosts(samplePosts) // This will replace existing entries with the same ID
        }
    }
}
