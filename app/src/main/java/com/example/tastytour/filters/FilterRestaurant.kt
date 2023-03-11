package com.example.tastytour.filters

import android.widget.Filter
import com.example.tastytour.adapters.AdapterRestaurant
import com.example.tastytour.models.ModelRestaurant

class FilterRestaurant: Filter{

    // Declare a private variable to hold a filtered list of ModelRestaurant objects
    private var filterList: ArrayList<ModelRestaurant>

    // Declare a private variable to hold an instance of the AdapterRestaurant class
    private var adapterRestaurant: AdapterRestaurant

    // Constructor that takes in a filterList of ModelRestaurant objects and an instance of the
    // AdapterRestaurant class
    constructor(filterList: ArrayList<ModelRestaurant>, adapterRestaurant: AdapterRestaurant) :
            super() {
        this.filterList = filterList
        this.adapterRestaurant = adapterRestaurant
    }

    // Override the performFiltering function to filter the list based on a given constraint
    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val results = FilterResults()

        // Check that the constraint is not null or empty
        if (constraint != null && constraint.isNotEmpty()) {

            // Set the constraint to uppercase for case sensitivity
            constraint = constraint.toString().uppercase()

            // Create a new ArrayList to hold the filtered ModelRestaurant objects
            val filteredModels: ArrayList<ModelRestaurant> = ArrayList()

            // Loop through the filterList to find matches for the constraint
            for (i in 0 until filterList.size) {
                if (filterList[i].restaurant.uppercase().startsWith(constraint)) {
                    filteredModels.add(filterList[i])
                }
            }

            // Set the results count to the size of the filteredModels ArrayList and set the values
            // to the filteredModels ArrayList
            results.count = filteredModels.size
            results.values = filteredModels
        } else {
            // If the constraint is null or empty, set the results count to the size of the
            // filterList and set the values to the filterList
            results.count = filterList.size
            results.values = filterList
        }

// Return the results
        return results

    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        adapterRestaurant.setData(results.values as ArrayList<ModelRestaurant>)
    }

}