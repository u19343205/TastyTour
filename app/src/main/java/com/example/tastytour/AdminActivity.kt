package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.databinding.ActivityAdminBinding
import com.example.tastytour.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish() //
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()


    }

    private fun checkUser() {
       val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() //

        }
        else{
            val name = firebaseUser.displayName
            binding.nameTv.text = name
        }
    }
}