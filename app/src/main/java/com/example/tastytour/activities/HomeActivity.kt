package com.example.tastytour.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.tastytour.*
import com.example.tastytour.databinding.ActivityHomeBinding
import com.example.tastytour.fragments.*
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    // Declare variables
    private lateinit var binding: ActivityHomeBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the activity
        setContentView(R.layout.activity_home)

        // Inflate the binding layout and set it as the content view
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Replace the default fragment with the HomeFragment
        replaceFragment(HomeFragment())

        // Set a listener for when an item in the bottom navigation bar is selected
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(HomeFragment()) // Replace with HomeFragment
                R.id.search -> replaceFragment(SearchFragment()) // Replace with SearchFragment
                R.id.saved -> replaceFragment(SavedFragment()) // Replace with SavedFragment
                R.id.reservations -> replaceFragment(ReservationsFragment()) // ReservationsFragment
                R.id.deals -> replaceFragment(DealsFragment()) // Replace with DealsFragment
                else -> { // Do nothing
                }
            }
            true // Return true to indicate that the item has been selected
        }

        // Get an instance of FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        // Check if the user is signed in or not
        checkUser()
    }

    // Function to check if the user is signed in or not
    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null) { // If the user is not signed in
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Start the MainActivity
            finish() // Finish the current activity

        } else { // If the user is signed in
            val name = firebaseUser.displayName // Get the user's display name
            binding.nameTv.text = name // Set the user's display name in the TextView
        }
    }

    // Function to replace the current fragment with a new fragment
    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager // Get the FragmentManager
        val fragmentTransaction =
            fragmentManager.beginTransaction() // Start a new FragmentTransaction
        fragmentTransaction.replace(
            R.id.frameLayout,
            fragment
        ) // Replace the current fragment with the new fragment
        fragmentTransaction.commit() // Commit the transaction
    }
}

