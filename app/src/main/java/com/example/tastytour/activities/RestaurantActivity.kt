package com.example.tastytour.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityRestaurantBinding

class RestaurantActivity : AppCompatActivity() {

    // Declares a private lateinit variable for binding to the ActivityRestaurantBinding class
    private lateinit var binding: ActivityRestaurantBinding

    // Overrides the onCreate function of the Activity class
    override fun onCreate(savedInstanceState: Bundle?) {
        // Calls the onCreate function of the parent class
        super.onCreate(savedInstanceState)

        // Sets the content view to the activity_restaurant.xml layout
        setContentView(R.layout.activity_restaurant)

        // Inflates the layout using ActivityRestaurantBinding
        binding = ActivityRestaurantBinding.inflate(layoutInflater)

        // Sets the content view to the root of the binding object
        setContentView(binding.root)

        // Sets a click listener for the address text view
        binding.addressTv.setOnClickListener {
            // Gets the text from the address text view
            val address = binding.addressTv.text
            // Creates a Uri for the location using the address
            val locationUri = Uri.parse("geo:0,0?q=$address")
            // Creates an Intent for viewing the location using Google Maps
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            // Sets the package to Google Maps
            mapIntent.setPackage("com.google.android.apps.maps")
            // Starts the activity for viewing the location in Google Maps
            startActivity(mapIntent)
        }

        // Sets a click listener for the menu text view
        binding.menuTv.setOnClickListener {
            // Calls the onMenuClick function
            onMenuClick()
        }

        // Sets a click listener for the Instagram image
        binding.instaIm.setOnClickListener {
            // Calls the gotoInsta function
            gotoInsta()
        }

        // Sets a click listener for the TikTok image
        binding.tiktokIm.setOnClickListener {
            // Calls the gotoTiktoK function
            gotoTiktoK()
        }

        // Sets a click listener for the website image
        binding.websiteIm.setOnClickListener {
            // Calls the gotoWebsite function
            gotoWebsite()
        }
    }

    // Function for opening the TikTok URL in a browser
    private fun gotoTiktoK() {
        // Creates a URL string for the TikTok page
        val url = "https://www.tiktok.com/@middletons_shg?_t=8aXKa8BmStK&_r=1"
        // Creates an Intent for viewing the URL in a browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Starts the activity for viewing the URL in a browser
        startActivity(intent)
    }

    // Function for opening the website URL in a browser
    private fun gotoWebsite() {
        // Creates a URL string for the website
        val url = "https://www.middletons-shg.co.uk/"
        // Creates an Intent for viewing the URL in a browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Starts the activity for viewing the URL in a browser
        startActivity(intent)
    }

    // Function for opening the Instagram URL in a browser
    private fun gotoInsta() {
        // Creates a URL string for the Instagram page
        val url = "https://instagram.com/middletons_shg?igshid=YmMyMTA2M2Y="
        // Creates an Intent for viewing the URL in a browser
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        // Starts the activity for viewing the URL in a browser
        startActivity(intent)
    }

    // Function for handling the menu click event
    private fun onMenuClick() {
        // Creates a URL string for the menu
        val url = "https://www.middletons-shg.co.uk/our-menu/leicester-menu"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}