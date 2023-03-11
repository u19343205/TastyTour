package com.example.tastytour.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declare binding, which will be used to access UI components in the layout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout of the activity
        setContentView(R.layout.activity_main)
        // Inflate the binding with the layoutInflater
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the content view to the root view of the binding
        setContentView(binding.root)

        // Set a click listener for the login button
        binding.loginButton.setOnClickListener {
            // Move to login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to login screen
        }

        // Set a click listener for the create account button
        binding.CreateButton.setOnClickListener {
            // Move to register activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to create account

        }
    }
}