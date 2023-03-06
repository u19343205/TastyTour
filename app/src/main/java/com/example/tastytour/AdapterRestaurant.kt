package com.example.tastytour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastytour.databinding.RowRestuarantBinding

class AdapterRestaurant :RecyclerView.Adapter<AdapterRestaurant.HolderRestaurant>{

    private val context: Context
    private val restaurantArrayList: ArrayList<ModelRestaurant>

    private lateinit var binding: RowRestuarantBinding

    constructor(context: Context, restaurantArrayList: ArrayList<ModelRestaurant>) {
        this.context = context
        this.restaurantArrayList = restaurantArrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRestaurant {
       //inflate bin rowresturant xml
        binding = RowRestuarantBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderRestaurant(binding.root)
    }


    override fun onBindViewHolder(holder: HolderRestaurant, position: Int) {
      // get data, set data, handle clicks
        val model = restaurantArrayList[position]
        val id = model.id
        val restaurant = model.restaurant
        val uid = model.uid
        val timestamp = model.timestamp

        holder.resTV.text = restaurant
        holder.saveButton.setOnClickListener{

        }
    }
    override fun getItemCount(): Int {
        return restaurantArrayList.size // how many in list
    }

    inner class HolderRestaurant(itemView: View): RecyclerView.ViewHolder(itemView){
        var resTV:TextView = binding.resTv
        var saveButton:ImageButton = binding.saveButton


    }



}