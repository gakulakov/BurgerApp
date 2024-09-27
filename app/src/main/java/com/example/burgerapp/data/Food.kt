package com.example.burgerapp.data

import java.io.Serializable

data class Food(
    val id: Long,
    val name: String,
    val subtitle: String,
    val description: String,
    val rating: Float,
    val imageRes: Int,
    val price: Float,
    var isFavorite: Boolean = false
) : Serializable