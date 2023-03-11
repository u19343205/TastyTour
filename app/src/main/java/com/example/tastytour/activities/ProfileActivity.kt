package com.example.tastytour.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Inflate the layout using the ViewBinding
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Go back to the HomeActivity
        binding.backButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to Profile screen
        }
        // Sign out and move to LoginActivity
        binding.signoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to Profile screen
        }
        // Toggle dark mode
        binding.darkmode.setOnClickListener {
            ToggleDarkMode()
        }
    }

    // Function to toggle dark mode (TODO: not yet implemented)
    private fun ToggleDarkMode() {
        TODO("Not yet implemented")
    }
}


