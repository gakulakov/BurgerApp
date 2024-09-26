package com.example.burgerapp.ui.screens.home.food_deail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodDetailViewModel: ViewModel() {
    private var _portionsCounter = MutableLiveData<Int>()
    val portionsCounter: LiveData<Int> get() = _portionsCounter

    private var _spicyLevel = MutableLiveData<Float>()
    val spicyLevel: LiveData<Float> get() = _spicyLevel

    init {
        _portionsCounter.value = 1
        _spicyLevel.value = 0.5f
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