package com.example.birthdaygreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Method 1 to handle button click
        //createBirthdayButton.setOnClickListener{}
    }

    // Method 2 to handle button click through xml file
    fun createBirthdayCard(view: View) {
        val name = nameInput.editableText.toString()
//        Toast.makeText(this,"name is $name",Toast.LENGTH_LONG).show()

        // To go to the next activity or ( to go to 1 process to another process)
        val intent = Intent(this,BirthdayGreetingActivity::class.java)
        intent.putExtra(BirthdayGreetingActivity.NAME_EXTRA,name)
        startActivity(intent)

    }
}