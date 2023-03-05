package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //loginpage
        binding.loginButton.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to login screen on back press

        }

        //create account page
        binding.CreateButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish() //

        }

    }
}