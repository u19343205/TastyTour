package com.example.tastytour

import android.widget.Filter
class FilterRestaurant: Filter{

    private var filterList: ArrayList<ModelRestaurant>

    private var adapterRestaurant: AdapterRestaurant

    //constructor
    constructor(filterList: ArrayList<ModelRestaurant>, adapterRestaurant: AdapterRestaurant) : super() {
        this.filterList = filterList
        this.adapterRestaurant = adapterRestaurant
    }

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint = constraint
        val results = FilterResults()

        //value should not be null and not empty
        if (constraint != null && constraint.isNotEmpty()) {

            //for case sensitivity
            constraint = constraint.toString().uppercase()
            val filteredModels: ArrayList<ModelRestaurant> = ArrayList()
            for (i in 0 until filterList.size) {
                if (filterList[i].restaurant.uppercase().contains(constraint)) {
                    filteredModels.add(filterList[i])

                }
            }
            results.count = filteredModels.size
            results.values = filteredModels
        } else {
            //if null or empty
            results.count = filterList.size
            results.values = filterList

        }
        return results
    }

    override fun publishResults(constraint: CharSequence?, results: FilterResults) {
        adapterRestaurant.restaurantArrayList = results.values as ArrayList<ModelRestaurant>

        //notify
        adapterRestaurant.notifyDataSetChanged()

    }
}