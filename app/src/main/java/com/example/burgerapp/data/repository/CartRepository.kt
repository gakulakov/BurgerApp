package com.example.burgerapp.data.repository

import com.example.burgerapp.data.dao.CartDao
import com.example.burgerapp.data.model.Food
import kotlinx.coroutines.flow.Flow

class CartRepository(private val cartDao: CartDao) {
    val allFoods: Flow<List<Food>> = cartDao.getCart()

    suspend fun add(food: Food) {
        cartDao.add(food)
    }

    suspend fun addAll(vararg food: Food) {
        cartDao.addAll(*food)
    }

    suspend fun remove(food: Food) {
        cartDao.remove(food)
    }
}