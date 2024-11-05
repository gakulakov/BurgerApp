package com.example.burgerapp.ui.screens.favorites

import androidx.lifecycle.ViewModel
import com.example.burgerapp.data.model.Food
import com.example.burgerapp.ui.screens.home.MOCK_FOODS
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FavoriteViewModel: ViewModel() {
    private val _foods = MutableStateFlow(MOCK_FOODS.filter { it.isFavorite })
    val foods: StateFlow<List<Food>> = _foods.asStateFlow()

    fun removeFavoriteFood(food: Food) {
        _foods.update { currentState ->
            val res = currentState.filter { it != food }

            res
        }
    }
}