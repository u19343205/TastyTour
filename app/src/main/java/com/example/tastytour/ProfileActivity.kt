package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //goback
        binding.backButton.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
            finish() //

        }

        //signout
        binding.signoutButton.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() //

        }






    }
}