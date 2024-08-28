package com.example.simpleapiapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.simpleapiapp.model.Post

@Dao
interface PostDao {

    @Query("SELECT * FROM posts")
    fun getAllPostsLiveData(): LiveData<List<Post>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<Post>)

    @Query("SELECT * FROM posts WHERE title LIKE :searchQuery OR body LIKE :searchQuery")
    fun searchPostsLiveData(searchQuery: String): LiveData<List<Post>>

    @Query("DELETE FROM posts WHERE id = :postId")
    suspend fun deletePostById(postId: Int): Int // Ensure this returns an Int

    @Update
    suspend fun updatePost(post: Post): Int // Ensure this returns an Int
}
