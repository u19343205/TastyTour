package com.example.tastytour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tastytour.databinding.ActivityAdminBinding
import com.example.tastytour.databinding.ActivityLoginBinding
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

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            finish() //
        }

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()
        loadRestaurants()






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
                adapterRestaurant = AdapterRestaurant(this@AdminActivity,restaurantArrayList)
                //set
                binding.restaurantsRv.adapter = adapterRestaurant
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}