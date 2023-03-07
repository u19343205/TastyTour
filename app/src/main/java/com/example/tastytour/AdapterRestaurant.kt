package com.example.tastytour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tastytour.databinding.RowRestuarantBinding

class AdapterRestaurant :RecyclerView.Adapter<AdapterRestaurant.HolderRestaurant>, Filterable{

    private val context: Context
    public var restaurantArrayList: ArrayList<ModelRestaurant>
    private var filterList: ArrayList<ModelRestaurant>

    private var filter: FilterRestaurant? = null

    private lateinit var binding: RowRestuarantBinding

    constructor(context: Context, restaurantArrayList: ArrayList<ModelRestaurant>) {
        this.context = context
        this.restaurantArrayList = restaurantArrayList
        this.filterList = restaurantArrayList
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
    }
    override fun getItemCount(): Int {
        return restaurantArrayList.size // how many in list
    }

    inner class HolderRestaurant(itemView: View): RecyclerView.ViewHolder(itemView){
        var resTV:TextView = binding.resTv

    }

    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterRestaurant(filterList, this)
        }
        return filter as FilterRestaurant
    }


}