package com.example.burgerapp.ui.screens.home

import androidx.lifecycle.ViewModel
import com.example.burgerapp.R
import com.example.burgerapp.data.Food
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

val MOCK_FOODS = listOf(
    Food(
        id = 1,
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.hamburger
    ),
    Food(
        id = 2,
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.cheeseburger,
        isFavorite = true
    ),
    Food(
        id = 3,
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5f,
        imageRes = R.drawable.chickenburger
    ),
    Food(
        id = 4,
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3f,
        imageRes = R.drawable.friedburger
    ),
    Food(
        id = 5,
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.hamburger
    ),
    Food(
        id = 6,
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.cheeseburger
    ),
    Food(
        id = 7,
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5f,
        imageRes = R.drawable.chickenburger
    ),
    Food(
        id = 8,
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3f,
        imageRes = R.drawable.friedburger
    ),
    Food(
        id = 9,
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.hamburger
    ),
    Food(
        id = 10,
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.cheeseburger,
        isFavorite = true
    ),
    Food(
        id = 11,
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5f,
        imageRes = R.drawable.chickenburger
    ),
    Food(
        id = 12,
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3f,
        imageRes = R.drawable.friedburger
    ),
    Food(
        id = 13,
        name = "Hamburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.hamburger
    ),
    Food(
        id = 14,
        name = "Cheesburger",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 4f,
        imageRes = R.drawable.cheeseburger
    ),
    Food(
        id = 15,
        name = "Freshroll",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 5f,
        imageRes = R.drawable.chickenburger
    ),
    Food(
        id = 16,
        name = "Frenchdog",
        subtitle = "Just Burger",
        description = "The Cheeseburger Wendy's Burger is a classic fast food burger that packs a punch of flavor in every bite. Made with a juicy beef patty cooked to perfection, it's topped with melted American cheese, crispy lettuce, ripe tomato, and crunchy pickles.",
        rating = 3f,
        imageRes = R.drawable.friedburger
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