package com.example.tastytour

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tastytour.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialise firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //initialise progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        //there is no account for the user
        binding.NoAccount.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish() //

        }


        //login
        //steps:
        //1. input data
        //2. validate data
        //3. login - firebase auth
        binding.loginButton.setOnClickListener {
            validateData()
        }


    }

    private var email = ""
    private var password = ""
    private fun validateData() {
        //input data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validate
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show()

        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()

        } else {
            LoginUser()
        }
    }

    private fun LoginUser() {
        progressDialog.setMessage("Logging In..")
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                // Move to homepage
                //show progress
                checkUser()
            }
            .addOnFailureListener{ e->
                //failed
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ${e.message}",Toast.LENGTH_SHORT).show()

            }
    }

    private fun checkUser() {
        progressDialog.setMessage("Checking User..")

        val firebaseUser = firebaseAuth.currentUser!!

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener{

                override fun onCancelled(error: DatabaseError) {

                }
                override fun onDataChange(snapshot: DataSnapshot){
                    progressDialog.dismiss()
                    val userType = snapshot.child("userType").value
                    if(userType =="user"){

                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                        finish() //

                    }
                    else if (userType =="admin"){

                        val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                        startActivity(intent)
                        finish() //

                    }

                }

            })

    }
    }






