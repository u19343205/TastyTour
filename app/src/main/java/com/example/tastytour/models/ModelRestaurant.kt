package com.example.tastytour.models

import com.example.tastytour.databinding.FragmentSavedBinding

// A class representing a restaurant model with various properties
class ModelRestaurant {
    fun favoriteButtonClickListener(fragmentSavedBinding: FragmentSavedBinding) {
        TODO("Not yet implemented")
    }

    val restaurantId: String = ""
    var id:String = ""
    var restaurant:String =""
    var location:String = ""
    var rating:String = ""
    var cuisine:String = ""
    var timestamp:Long = 0
    var uid:String = ""
    var imageUrl: String = ""
    // A flag indicating whether the restaurant is a favorite or not
    var isFavorite: Boolean = false

    //empty constructor
    constructor()

    // A parameterized constructor to initialise some of the properties
    constructor(id: String, restaurant: String, timestamp: Long, uid: String) {
        this.id = id
        this.restaurant = restaurant
        this.timestamp = timestamp
        this.location = location
        this.rating = rating
        this.cuisine = cuisine
        this.uid = uid
        this.imageUrl = imageUrl
    }

    constructor(p0: Any, p1: Any, p2: Any)


}