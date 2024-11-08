package com.example.burgerapp.ui.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import com.example.burgerapp.R
import com.example.burgerapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val fadeTransition = Fade()


        enterTransition = fadeTransition
        exitTransition = fadeTransition

        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, statusBarHeight.top, 0, 0)
            insets
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categories = listOf("All", "Combos", "Sliders", "Classic")
        val categoryAdapter = CategoryAdapter(categories, 0)

        val categoriesRecyclerView = binding.rvCategories
        categoriesRecyclerView.addItemDecoration(
            CategorySpacingHorizontal(resources.getDimensionPixelSize(R.dimen.margin))
        )
        categoriesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView.adapter = categoryAdapter

        val homeViewModel: HomeViewModel by viewModels()

        categoryAdapter.onItemClick = { category ->
            homeViewModel.changeSelectedTab(category)
        }

        lifecycleScope.launch {
            homeViewModel.selectedTab.collect {
                categoryAdapter.setActiveButton(it)
            }
        }



        val foodAdapter = FoodAdapter()
        foodAdapter.setData(homeViewModel.foods.value)
        val foodsRecyclerView = binding.rvFoods

        foodsRecyclerView.isNestedScrollingEnabled = false
        foodsRecyclerView.addItemDecoration(GridSpacingItemDecoration(2, 44, false))
        foodsRecyclerView.layoutManager = GridLayoutManager(context, 2)
        foodsRecyclerView.adapter = foodAdapter

        foodAdapter.onItemClick = { foodText, foodImage, food ->
            val action = HomeFragmentDirections.actionHomeToFoodDetails(food.imageRes, food)
            val extras = FragmentNavigatorExtras(
                foodImage to "shared_image_${food.uuid}",
                foodText to "shared_text_${food.uuid}",
            )
            findNavController().navigate(action, extras)
        }
        foodAdapter.onFavoriteClick = { food ->
            homeViewModel.changeFavoriteFood(food, !food.isFavorite)
        }

        lifecycleScope.launch {
            homeViewModel.foods.collect {
                foodAdapter.setNewData(it)
            }
        }

        postponeEnterTransition()
        foodsRecyclerView.doOnPreDraw {
            startPostponedEnterTransition()
        }
    }
}