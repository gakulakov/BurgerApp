package com.example.burgerapp.ui.screens.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.burgerapp.data.repository.CartRepository
import com.example.burgerapp.data.model.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartRepository): ViewModel() {
    private var _portionsCounter = MutableLiveData<Int>()
    val portionsCounter: LiveData<Int> get() = _portionsCounter

    val cardFoods = repository.allFoods

    private var _spicyLevel = MutableLiveData<Float>()
    val spicyLevel: LiveData<Float> get() = _spicyLevel

    init {
        _portionsCounter.value = 1
        _spicyLevel.value = 0.5f

    }

    fun addFoodToCart(food: Food) = viewModelScope.launch {
        val foods = List(portionsCounter.value!!) { food }
        repository.addAll(*foods.toTypedArray())
    }

    fun removeFromCart(food: Food) = viewModelScope.launch {
        repository.remove(food)
    }

    fun addPortion() {
        _portionsCounter.value = (_portionsCounter.value ?: 1) + 1
    }

    fun removePortion() {
        if (_portionsCounter.value != null && _portionsCounter.value!! > 0) {
            _portionsCounter.value = _portionsCounter.value!! - 1
        }
    }

    fun changeSpicyLevel(level: Float) {
        _spicyLevel.value = level
    }
}