package com.example.tastytour

class ModelRestaurant {

    var id:String = ""
    var restaurant:String =""
    var timestamp:Long = 0
    var uid:String = ""

    //empty constructor
    constructor()

    //parameterised constructor
    constructor(id: String, restaurant: String, timestamp: Long, uid: String) {
        this.id = id
        this.restaurant = restaurant
        this.timestamp = timestamp
        this.uid = uid
    }



}