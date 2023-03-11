@file:Suppress("DEPRECATION")

package com.example.tastytour.activities

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    //declare variables
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog
    private var name = ""
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set content view using binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //initialize progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        //go back
        binding.backButton.setOnClickListener{
            // Move back to main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity
        }

        //register user
        //steps:
        // 1. input data
        // 2. validate data
        // 3. create account - firebase auth
        // 4. save user data - firebase realtime database
        binding.registerButton.setOnClickListener{
            validateData()
        }
    }

    //function to validate user data
    private fun validateData(){

        //get input data
        name = binding.nameEt.text.toString().trim()
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        val cPassword = binding.conpasswordEt.text.toString().trim()

        //validate data
        if (name.isEmpty()){
            //name is blank
            Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT).show()
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email address
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty()){
            //password is blank
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT).show()
        }
        else if (password != cPassword){
            //password and confirm password don't match
            Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
        }
        else{
            //if data is valid, create user account
            createuserAccount()
        }
    }

    //function to create user account
    private fun createuserAccount() {

        //show progress dialog
        progressDialog.setMessage("Creating Account..")
        progressDialog.show()

        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //creating account
                //if account created successfully, update user info
                updateUserInfo()
            }
            .addOnFailureListener{ e->
                //failed to create account
                progressDialog.dismiss()
                Toast.makeText(this,"Failed to create account due to ${e.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }

    //function to update user info after account creation
    private fun updateUserInfo() {

        //show progress dialog
        progressDialog.setMessage("Saving user details..")

        //get current timestamp
        val timestamp = System.currentTimeMillis()

        //get current user uid
        val uid = firebaseAuth.uid

        //add user info into hash map
        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["name"] = name
        hashMap["email"] = email
        hashMap["profileImage"] = "" //add as empty can be edited in profile
        hashMap["userType"] = "user" //admin or user
        hashMap["timestamp"] = timestamp

        //upload data into database
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                //user added
                progressDialog.dismiss()
                Toast.makeText(this,"Account created",Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{e ->
                //failed to add user
                //failed to create account
                progressDialog.dismiss()
                Toast.makeText(this,"Failed to add user due to ${e.message}",
                    Toast.LENGTH_SHORT).show()

            }



    }
}