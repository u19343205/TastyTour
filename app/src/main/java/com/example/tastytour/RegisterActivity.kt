@file:Suppress("DEPRECATION")

package com.example.tastytour

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.tastytour.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialise firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //initialise progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        //go back
        binding.backButton.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() //
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
    private var name = ""
    private var email = ""
    private var password = ""


    private fun validateData(){
        //input data
        name = binding.nameEt.text.toString().trim()
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()
        val cPassword = binding.conpasswordEt.text.toString().trim()

        //validate data
        if (name.isEmpty()){
            //name is black
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
            Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
        }
        else{
            createuserAccount()
        }
    }

    private fun createuserAccount() {
       //create account through firebase auth
        progressDialog.setMessage("Creating Account..")
        progressDialog.show()

        //create user in firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                //creating account
               updateUserInfo()
            }
            .addOnFailureListener{ e->
                //failed to create account
                progressDialog.dismiss()
                Toast.makeText(this,"Failed to create account due to ${e.message}",Toast.LENGTH_SHORT).show()
            }
    }
    //save user info
    private fun updateUserInfo() {
     progressDialog.setMessage("Saving user details..")

        //timestamp
        val timestamp = System.currentTimeMillis()

       // get current user uid, as user is registered
        val uid = firebaseAuth.uid

        //add user into database
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
                Toast.makeText(this,"Failed to add user due to ${e.message}",Toast.LENGTH_SHORT).show()

            }



    }
}