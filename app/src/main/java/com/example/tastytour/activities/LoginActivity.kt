package com.example.tastytour.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding // View binding object for login activity
    private lateinit var firebaseAuth: FirebaseAuth // Firebase authentication object

    // Public getter method to access firebaseAuth
    fun getFirebaseAuth(): FirebaseAuth {
        return firebaseAuth
    }
    private lateinit var progressDialog: ProgressDialog // Progress dialog to show loading messages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Set layout file for activity
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root) // Set root view for the activity

        // Initialize Firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance()

        // Initialize progress dialog and set properties
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        // Open register activity when user doesn't have an account
        binding.NoAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish() // Finish current activity
        }

        // Validate and log in user when login button is clicked
        binding.loginButton.setOnClickListener {
            validateData()
        }
    }
    private var email = ""
    private var password = ""

    private fun validateData() {
        // Get user input
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        // Validate user input
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()

        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()

        } else {
            LoginUser() // Log in user if input is valid
        }
    }

    private fun LoginUser() {
        progressDialog.setMessage("Logging In..")
        progressDialog.show()

        // Sign in user using Firebase authentication
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // If successful, check user type and move to corresponding activity
                checkUser()
            }
            .addOnFailureListener { e ->
                // If login fails, dismiss progress dialog and show error message
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to ${e.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser() {
        progressDialog.setMessage("Checking User..")

        // Get current Firebase user and check their user type
        val firebaseUser = firebaseAuth.currentUser!!
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {}

                override fun onDataChange(snapshot: DataSnapshot) {
                    progressDialog.dismiss()
                    val userType = snapshot.child("userType").value
                    if (userType == "user") {
                        // Move to home activity for regular users
                        val intent = Intent(this@LoginActivity, HomeActivity::class
                            .java)
                        startActivity(intent)
                        finish() // Finish current activity
                    } else if (userType == "admin") {
                        // Move to admin activity for admin users
                        val intent = Intent(this@LoginActivity, AdminActivity::class
                            .java)
                        startActivity(intent)
                        finish() // Finish current activity
                    }
                }
            })
    }
}





