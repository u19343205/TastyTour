package com.example.tastytour

class ModelRestaurant {

    val restaurantId: String = ""
    var id:String = ""
    var restaurant:String =""
    var location:String = ""
    var rating:String = ""
    var cuisine:String = ""
    var timestamp:Long = 0
    var uid:String = ""
    var imageUrl: String = ""
    var isFavorite: Boolean = true // Add this property



    //empty constructor
    constructor()

    //parameterised constructor
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



}