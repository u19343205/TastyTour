package com.example.tastytour

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.tastytour.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRestaurantBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        binding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addressTv.setOnClickListener {
            val address = binding.addressTv.text
            val locationUri = Uri.parse("geo:0,0?q=$address")
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)

        }
        binding.menuTv.setOnClickListener {
            onMenuClick()


        }

    }

    private fun onMenuClick() {
        val url = "https://www.middletons-shg.co.uk/our-menu/leicester-menu/"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}