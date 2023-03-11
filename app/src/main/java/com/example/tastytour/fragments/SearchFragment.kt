package com.example.tastytour.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tastytour.adapters.AdapterRestaurant
import com.example.tastytour.models.ModelRestaurant
import com.example.tastytour.databinding.FragmentSearchBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    //array list for restaurants
    private lateinit var binding: FragmentSearchBinding
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
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchet.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //called when user is typing
                try{
                    adapterRestaurant.filter.filter(s)
                }
                catch(e: Exception){

                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
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
                    AdapterRestaurant(requireContext(), restaurantArrayList, layoutType = 2)
                //set
                binding.restaurantsRv.adapter = adapterRestaurant
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


}