package com.example.tastytour.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.tastytour.filters.FilterRestaurant
import com.example.tastytour.models.ModelRestaurant
import com.example.tastytour.R
import com.example.tastytour.databinding.RowRestuarantBinding
import com.example.tastytour.databinding.Row2RestuarantBinding
import com.example.tastytour.databinding.DealsRestuarantBinding
import com.example.tastytour.fragments.SavedFragment

class AdapterRestaurant(private val context: Context, private var restaurantArrayList:
ArrayList<ModelRestaurant>, private val layoutType: Int) :
    RecyclerView.Adapter<AdapterRestaurant.HolderRestaurant>(), Filterable {

    // Initialize variables
    private var filterList: ArrayList<ModelRestaurant> = restaurantArrayList
    private var filter: FilterRestaurant? = null

    // Define a function to create a new instance of ViewHolder when RecyclerView needs it
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderRestaurant {
        // Inflate the appropriate view based on the layout type provided
        val binding: ViewBinding = when (layoutType) {
            1 -> RowRestuarantBinding.inflate(LayoutInflater.from(context), parent
                , false)
            2 -> Row2RestuarantBinding.inflate(LayoutInflater.from(context), parent
                , false)
            3 -> DealsRestuarantBinding.inflate(LayoutInflater.from(context), parent
                , false)
            else -> throw IllegalArgumentException("Invalid layout type provided: $layoutType")
        }
        // Return a new instance of ViewHolder
        return HolderRestaurant(binding.root, binding)
    }

    // Define a function to bind data to views in ViewHolder
    override fun onBindViewHolder(holder: HolderRestaurant, position: Int) {
        val model = restaurantArrayList[position]

        // Set data to views based on the layout type provided
        if (layoutType == 1) {
            val binding = holder.binding as RowRestuarantBinding
            binding.resTv.text = model.restaurant
            binding.locationTv.text = model.location
            binding.ratingTv.text = model.rating
            binding.cuisineTv.text = model.cuisine

            // Define onClickListener for favorite button
            binding.faveTv.setOnClickListener {
                model.isFavorite = !model.isFavorite
                if (model.isFavorite) {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_24)
                    Toast.makeText(binding.root.context, "Added to favorites",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                    Toast.makeText(
                        binding.root.context,
                        "Removed from favorites",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Define onClickListener for the whole row
            binding.root.setOnClickListener {
                // Create a new instance of destination fragment
                val destinationFragment = SavedFragment()
                // Pass data for the selected card as arguments to the destination fragment
                val bundle = Bundle().apply {
                    putString("restaurant", model.restaurant)
                    putString("location", model.location)
                }
                destinationFragment.arguments = bundle
                // Navigate to the destination fragment
                val transaction =
                    (binding.root.context as AppCompatActivity).supportFragmentManager.
                    beginTransaction()
                transaction.replace(R.id.restaurantsRv, destinationFragment)
                transaction.addToBackStack(null)
                transaction.commit()
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
                    Toast.makeText(binding.root.context, "Added to favorites",
                        Toast.LENGTH_SHORT)
                        .show()

                } else {
                    binding.faveTv.setBackgroundResource(R.drawable.baseline_favorite_border_24)
                    Toast.makeText(
                        binding.root.context,
                        "Removed from favorites",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
            binding.root.setOnClickListener {
                val destinationFragment = SavedFragment()
                // Pass data for the selected card as arguments to the destination fragment
                val bundle = Bundle().apply {
                    putString("restaurant", model.restaurant)
                    putString("location", model.location)
                    // add more data if needed
                }
                destinationFragment.arguments = bundle
                // Navigate to the destination fragment

                //find Fragment Manager and starts a transaction.
                val transaction =
                    (binding.root.context as AppCompatActivity).supportFragmentManager.
                    beginTransaction()
                // replace the current RecyclerView with the details fragment.
                transaction.replace(R.id.restaurantsRv, destinationFragment)
                // add current fragment to the back stack.
                transaction.addToBackStack(null)
                // commits the transaction.
                transaction.commit()
            }
        }
// If the layout type is 3, it means it's a "Deals Restaurant" layout.
        else if (layoutType == 3) {
            val binding = holder.binding as DealsRestuarantBinding
            binding.resTv.text = model.restaurant
            binding.locationTv.text = model.location
            binding.cuisineTv.text = model.cuisine
        }
    }

    //returns the number of items in the list.
    override fun getItemCount(): Int {
        return restaurantArrayList.size
    }

    //sets the data to the Adapter.
    fun setData(data: ArrayList<ModelRestaurant>) {
        restaurantArrayList = data
        notifyDataSetChanged()
    }

    //returns the filter for the Adapter.
    override fun getFilter(): Filter {
        if (filter == null) {
            filter = FilterRestaurant(filterList, this)
        }
        return filter!!
    }

    // hold the view and binding.
    inner class HolderRestaurant(itemView: View, val binding: ViewBinding) :
        RecyclerView.ViewHolder(itemView)
}