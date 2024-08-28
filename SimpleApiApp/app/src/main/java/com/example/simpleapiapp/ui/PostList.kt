package com.example.simpleapiapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.simpleapiapp.model.Post
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun PostList(viewModel: PostViewModel) {
    var isRefreshing by remember { mutableStateOf(false) }
    var selectedPost by remember { mutableStateOf<Post?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    val posts by viewModel.getAllPostsLiveData().observeAsState(emptyList())

    // Filter posts based on search query
    val filteredPosts = posts.filter {
        it.title.contains(searchQuery, ignoreCase = true) ||
                it.body.contains(searchQuery, ignoreCase = true)
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            isRefreshing = true
            viewModel.refreshPosts {
                isRefreshing = false
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { query -> searchQuery = query },
                label = { Text("Search") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Display posts
            filteredPosts.forEach { post ->
                PostItem(
                    post = post,
                    onDelete = { postId ->
                        viewModel.deletePostById(postId)
                    },
                    onEdit = { updatedPost ->
                        selectedPost = updatedPost
                    }
                )
            }
        }
    }

    selectedPost?.let { post ->
        EditPostDialog(
            post = post,
            onDismiss = { selectedPost = null },
            onSave = { updatedPost ->
                viewModel.updatePost(updatedPost)
                selectedPost = null
            }
        )
    }
}
