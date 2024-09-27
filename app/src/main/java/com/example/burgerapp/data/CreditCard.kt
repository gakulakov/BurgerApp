package com.example.burgerapp.data

data class CreditCard(
    val id: Int,
    val name: String,
    val serialNumber: String,
    val imageRes: Int,
    var isChecked: Boolean,
)
