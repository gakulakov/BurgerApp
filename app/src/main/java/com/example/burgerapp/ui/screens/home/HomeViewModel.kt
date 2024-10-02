package com.example.burgerapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.burgerapp.R
import com.example.burgerapp.data.model.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

val MOCK_FOODS = listOf(
    Food(
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.hamburger,
        price = 19.99
    ),
    Food(
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.cheeseburger,
        price = 9.99,
        isFavorite = true
    ),
    Food(
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5.0,
        imageRes = R.drawable.chickenburger,
        price = 12.99
    ),
    Food(
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3.8,
        imageRes = R.drawable.friedburger,
        price = 18.24
    ),
    Food(
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.hamburger,
        price = 18.24
    ),
    Food(
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.cheeseburger,
        price = 18.24
    ),
    Food(
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5.0,
        imageRes = R.drawable.chickenburger,
        price = 18.24
    ),
    Food(
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3.0,
        imageRes = R.drawable.friedburger,
        price = 18.24
    ),
    Food(
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.hamburger,
        price = 18.24
    ),
    Food(
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.cheeseburger,
        price = 18.24,
        isFavorite = true
    ),
    Food(
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5.0,
        imageRes = R.drawable.chickenburger,
        price = 18.24
    ),
    Food(
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3.7,
        imageRes = R.drawable.friedburger,
        price = 18.24
    ),
    Food(
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.hamburger,
        price = 18.24
    ),
    Food(
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4.0,
        imageRes = R.drawable.cheeseburger,
        price = 18.24
    ),
    Food(
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5.0,
        imageRes = R.drawable.chickenburger,
        price = 18.24
    ),
    Food(
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3.0,
        imageRes = R.drawable.friedburger,
        price = 18.24
    ),
)

class HomeViewModel : ViewModel() {
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()



    fun changeSelectedTab(position: Int) {
        _selectedTab.update { position }
    }

    private val _foods = MutableStateFlow(MOCK_FOODS)
    val foods: StateFlow<List<Food>> = _foods.asStateFlow()

    fun changeFavoriteFood(food: Food, isFavorite: Boolean) {
        _foods.update { currentState ->
            val res = currentState.map { currentFood ->
                if (currentFood.id == food.id) {
                    currentFood.copy(
                        isFavorite = isFavorite
                    )
                } else {
                    currentFood
                }
            }
            res
        }
    }

}