package com.example.tastytour.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivitySplashBinding
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    // Declare a lateinit variable for the View Binding of the activity
    private lateinit var binding: ActivitySplashBinding

    // Declare a lateinit variable for Firebase authentication instance
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the activity layout to activity_splash.xml
        setContentView(R.layout.activity_splash)

        // Inflate the layout using View Binding
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a delay of 2 seconds before starting the MainActivity
        Handler().postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
        }, 2000)

    }

}