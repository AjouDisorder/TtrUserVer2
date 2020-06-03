package com.example.ttruserver2.Fragment

import java.io.Serializable

data class TimeSearchListModel (
    val menuPicture: Int,
    val menuTitle: String,
    val startTime: String,
    val endTime: String,
    val menuDistance: Double,
    val quantity: Int,
    val discount: Int,
    val originPrice: Int,
    val discountedPrice: Int,
    val menuId: String,
    val restaurantId: String,
    val restaurantTitle: String,
    val type: String,
    val method: String
)