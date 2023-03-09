package com.example.tastytour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
        var location = model.location
        var rating = model.rating
        var cuisine = model.cuisine
        val imageUrl = model.imageUrl


        holder.resTv.text = restaurant
        holder.locationTv.text = location
        holder.ratingTv.text = rating
        holder.cuisineTv.text = cuisine


    }
    override fun getItemCount(): Int {
        return restaurantArrayList.size // how many in list
    }

    inner class HolderRestaurant(itemView: View): RecyclerView.ViewHolder(itemView){
        var resTv:TextView = binding.resTv
        var locationTv: TextView = binding.locationTv
        var ratingTv: TextView = binding.ratingTv
        var cuisineTv: TextView = binding.cuisineTv



    }

    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterRestaurant(filterList, this)
        }
        return filter as FilterRestaurant
    }


}