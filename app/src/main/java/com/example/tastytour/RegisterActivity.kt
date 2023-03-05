package com.example.tastytour

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.mbms.DownloadProgressListener
import android.util.Patterns
import android.widget.Toast
import com.example.tastytour.databinding.ActivityMainBinding
import com.example.tastytour.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

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
            Toast.makeText(this,"Enter Name",Toast.LENGTH_SHORT.show())
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //invalid email address
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_SHORT.show())

        }
        else if(password.isEmpty()){
            Toast.makeText(this,"Enter Password",Toast.LENGTH_SHORT.show())
        }

    }
}