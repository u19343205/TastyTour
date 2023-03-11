package com.example.tastytour.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.adapters.AdapterRestaurant
import com.example.tastytour.models.ModelRestaurant
import com.example.tastytour.R
import com.example.tastytour.databinding.ActivityAdminBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAdminBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var restaurantArrayList: ArrayList<ModelRestaurant>
    //adapter
    private lateinit var adapterRestaurant: AdapterRestaurant

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //add restaurant
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish() //
        }

        //logout button
        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() //
        }

        //init
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        loadRestaurants()

    }

    //check the user is authorised
    private fun checkUser() {
       val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser == null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() //

        }
        else{

        }
    }

    private fun loadRestaurants() {
        //initalise arry
        restaurantArrayList = ArrayList()

        //get from db
        val ref = FirebaseDatabase.getInstance().getReference("Restaurants")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //clear list before starting
                restaurantArrayList.clear()
                for (ds in snapshot.children){
                    val model = ds.getValue(ModelRestaurant::class.java)

                    //add
                    restaurantArrayList.add(model!!)
                }
                //setupadapter
                adapterRestaurant = AdapterRestaurant(this@AdminActivity,restaurantArrayList
                    , layoutType = 2)
                //set
                binding.restaurantsRv.adapter = adapterRestaurant
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}