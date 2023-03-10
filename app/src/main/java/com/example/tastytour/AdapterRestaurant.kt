package com.example.tastytour

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.tastytour.databinding.RowRestuarantBinding
import com.example.tastytour.databinding.Row2RestuarantBinding
import com.example.tastytour.databinding.DealsRestuarantBinding
class AdapterRestaurant(private val context: Context, private var restaurantArrayList: ArrayList<ModelRestaurant>, private val layoutType: Int) : RecyclerView.Adapter<AdapterRestaurant.HolderRestaurant>(), Filterable {

    private var filterList: ArrayList<ModelRestaurant> = restaurantArrayList
    private var filter: FilterRestaurant? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRestaurant {
        val binding: ViewBinding = when (layoutType) {
            1 -> RowRestuarantBinding.inflate(LayoutInflater.from(context), parent, false)
            2 -> Row2RestuarantBinding.inflate(LayoutInflater.from(context), parent, false)
            3 -> DealsRestuarantBinding.inflate(LayoutInflater.from(context), parent, false)
            else -> throw IllegalArgumentException("Invalid layout type provided: $layoutType")
        }
        return HolderRestaurant(binding.root, binding)
    }

    override fun onBindViewHolder(holder: HolderRestaurant, position: Int) {
        val model = restaurantArrayList[position]

        // set data to views
        if (layoutType == 1) {
            val binding = holder.binding as RowRestuarantBinding
            binding.resTv.text = model.restaurant
            binding.locationTv.text = model.location
            binding.ratingTv.text = model.rating
            binding.cuisineTv.text = model.cuisine
            binding.faveTv.setOnClickListener {
                model.isFavorite = !model.isFavorite
                if (model.isFavorite) {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_24)
                } else {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                }
            }

        } else if (layoutType == 2) {
            val binding = holder.binding as Row2RestuarantBinding
            binding.resTv.text = model.restaurant
            binding.locationTv.text = model.location
            binding.ratingTv.text = model.rating
            binding.cuisineTv.text = model.cuisine
            binding.faveTv.setOnClickListener {
                model.isFavorite = !model.isFavorite
                if (model.isFavorite) {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_24)
                } else {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                }
            }
        }
        else if (layoutType == 3) {
            val binding = holder.binding as DealsRestuarantBinding
            binding.resTv.text = model.restaurant
            binding.locationTv.text = model.location
            binding.cuisineTv.text = model.cuisine
        }
    }

    override fun getItemCount(): Int {
        return restaurantArrayList.size
    }

    fun setData(data: ArrayList<ModelRestaurant>) {
        restaurantArrayList = data
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        if (filter == null) {
            filter = FilterRestaurant(filterList, this)
        }
        return filter!!
    }

    inner class HolderRestaurant(itemView: View, val binding: ViewBinding) : RecyclerView.ViewHolder(itemView)
}
