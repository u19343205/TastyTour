package com.example.tastytour

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tastytour.databinding.ActivityAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialise firebase
        firebaseAuth = FirebaseAuth.getInstance()

        //initialise progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        binding.submitButton.setOnClickListener{
            validateData()
        }
    }

    private var restaurant = ""

    private fun validateData(){
        restaurant = binding.resET.text.toString().trim()

        if(restaurant.isEmpty()){
            Toast.makeText(this, "Enter Restaurant", Toast.LENGTH_SHORT).show()

        }
        else{
            addRestaurant()
        }
    }

    private fun addRestaurant(){
        progressDialog.show()
        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
        hashMap["restaurant"] = restaurant
        hashMap["timestamp"] = timestamp
        hashMap["uid"] = "${firebaseAuth.uid}"

         val ref = FirebaseDatabase.getInstance().getReference("Restaurants")
        ref.child("$timestamp")
            .setValue(hashMap)
            .addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()


            }
            .addOnFailureListener{e->
                progressDialog.dismiss()
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()



            }
    }




}