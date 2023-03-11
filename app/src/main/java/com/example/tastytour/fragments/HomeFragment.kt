package com.example.tastytour.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastytour.adapters.AdapterRestaurant
import com.example.tastytour.models.ModelRestaurant
import com.example.tastytour.activities.ProfileActivity
import com.example.tastytour.databinding.FragmentHomeBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    //array list for restaurants
    private lateinit var restaurantArrayList: ArrayList<ModelRestaurant>
    //adapter
    private lateinit var adapterRestaurant: AdapterRestaurant

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        //there is no account for the user
        binding.profileutton.setOnClickListener {
            val intent = Intent(requireContext(), ProfileActivity::class.java)
            startActivity(intent)

        }

        loadRestaurants()

        return binding.root

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
                adapterRestaurant =
                    AdapterRestaurant(requireContext(), restaurantArrayList, layoutType = 1)
                //set
                binding.restaurantsRv.adapter = adapterRestaurant
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}