package com.example.simpleapiapp.data.repository

import androidx.lifecycle.LiveData
import com.example.simpleapiapp.data.database.PostDao
import com.example.simpleapiapp.data.network.ApiService
import com.example.simpleapiapp.model.Post

class PostRepository(
    private val postDao: PostDao,
    private val apiService: ApiService
) {

    fun getAllPostsLiveData(): LiveData<List<Post>> = postDao.getAllPostsLiveData()

    suspend fun insertPosts(posts: List<Post>) {
        postDao.insertPosts(posts)
    }

    fun searchPostsLiveData(searchQuery: String): LiveData<List<Post>> =
        postDao.searchPostsLiveData("%$searchQuery%")

    suspend fun deletePostById(postId: Int) {
        postDao.deletePostById(postId)
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePost(post)
    }

    suspend fun refreshPosts() {
        val response = apiService.getPosts()
        postDao.insertPosts(response)
    }

}
