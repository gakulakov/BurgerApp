package com.example.burgerapp.data.model

data class CreditCard(
    val id: Int,
    val backgroundColorResId: Int,
    val name: String,
    val serialNumber: String,
    val imageRes: Int,
    var isChecked: Boolean,
)
