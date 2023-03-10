package com.example.tastytour

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.tastytour.databinding.ActivityAddBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private lateinit var imageView: ImageView


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
        //there is no account for the user
        binding.backButton.setOnClickListener {
            // Move to homepage
            val intent = Intent(this, AdminActivity::class.java)
            startActivity(intent)
            finish() //

        }
        binding.uploadButton.setOnClickListener {
            // open image gallery
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "image/*"
            startActivityForResult(intent, 1)
        }

    }

    private var restaurant = ""
    private var location = ""
    private var cuisine = ""
    private var rating = ""
    private var imageUrl = ""
    private var imageUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            Glide.with(this).load(imageUri).into(imageView)
            imageUrl = "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2F${imageUri?.lastPathSegment}?alt=media"
        }
    }

    private fun validateData(){
        restaurant = binding.resET.text.toString().trim()
        location = binding.locationET.text.toString().trim()
        cuisine = binding.cuisineET.text.toString().trim()
        rating = binding.ratingsET.text.toString().trim()
        imageUrl = "https://firebasestorage.googleapis.com/v0/b/my-app.appspot.com/o/images%2F${imageUri?.lastPathSegment}?alt=media"


        if(restaurant.isEmpty()){
            Toast.makeText(this, "Enter Restaurant", Toast.LENGTH_SHORT).show()

        }
        else{
            uploadImage()
        }
    }
    private fun uploadImage() {
        if (imageUri == null) {
            // image is not selected, skip image upload
            addRestaurant()
        } else {
            val storageRef = FirebaseStorage.getInstance().reference.child("images").child("${System.currentTimeMillis()}")
            storageRef.putFile(imageUri!!)
                .addOnSuccessListener {
                    storageRef.downloadUrl
                        .addOnSuccessListener {uri: Uri ->
                            imageUrl = uri.toString()
                            addRestaurant()
                        }
                        .addOnFailureListener {
                            progressDialog.dismiss()
                            Toast.makeText(this, "Failed to get download URL", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun addRestaurant(){
        progressDialog.show()
        val timestamp = System.currentTimeMillis()

        val hashMap = HashMap<String, Any>()
        hashMap["restaurant"] = restaurant
        hashMap["location"] = location
        hashMap["rating"] = rating
        hashMap["cuisine"] = cuisine
        hashMap["timestamp"] = timestamp
        hashMap["uid"] = "${firebaseAuth.uid}"
        hashMap["image"] = imageUrl

         val ref = FirebaseDatabase.getInstance().getReference("Restaurants")
        ref.push()
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