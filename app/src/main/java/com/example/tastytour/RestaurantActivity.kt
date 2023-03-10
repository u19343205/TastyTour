package com.example.tastytour

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tastytour.databinding.ActivityProfileBinding
import com.example.tastytour.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addressTv.setOnClickListener {
            // Replace the "1600 Amphitheatre Parkway, Mountain View, CA" with your desired location
            val address = binding.addressTv.text
            val locationUri = Uri.parse("geo:0,0?q=$address")
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }
    }
}