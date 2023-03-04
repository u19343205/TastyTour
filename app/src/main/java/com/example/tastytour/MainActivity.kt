package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tastytour.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginButton.setOnClickListener {
            if (binding.emailLogin.text.isNullOrBlank() && binding.passwordLogin.text.isNullOrBlank()) {
                Toast.makeText(this, "Please fill the required details", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "${binding.emailLogin.text} is logged in", Toast.LENGTH_SHORT)
                    .show()
            }
            // Move to homepage
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to login screen on back press
        }
    }
}
