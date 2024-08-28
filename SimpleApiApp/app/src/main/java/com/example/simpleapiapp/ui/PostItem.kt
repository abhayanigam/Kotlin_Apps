package com.example.simpleapiapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simpleapiapp.model.Post

@Composable
fun PostItem(post: Post, onDelete: (Int) -> Unit, onEdit: (Post) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        EditPostDialog(
            post = post,
            onSave = { updatedPost ->
                onEdit(updatedPost)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "User ID: ${post.userId}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Title: ${post.title}", style = MaterialTheme.typography.titleLarge)
            Text(text = "Body: ${post.body}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = { onDelete(post.id) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Delete")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { showDialog = true },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Edit")
                }
            }
        }
    }
}
