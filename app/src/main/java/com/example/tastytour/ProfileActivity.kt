package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.databinding.ActivityLoginBinding
import com.example.tastytour.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //there is no account for the user
        binding.backButton.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
            finish() //

        }






    }
}