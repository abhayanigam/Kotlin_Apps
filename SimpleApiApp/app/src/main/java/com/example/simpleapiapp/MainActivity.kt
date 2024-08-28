package com.example.simpleapiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.simpleapiapp.di.Injection
import com.example.simpleapiapp.ui.PostList
import com.example.simpleapiapp.ui.PostViewModel
import com.example.simpleapiapp.ui.theme.SimpleApiAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, Injection.providePostViewModelFactory(applicationContext)).get(PostViewModel::class.java)

        // Add sample data
        viewModel.addSampleData()

        setContent {
            SimpleApiAppTheme {
                PostList(viewModel)
            }
        }
    }
}
