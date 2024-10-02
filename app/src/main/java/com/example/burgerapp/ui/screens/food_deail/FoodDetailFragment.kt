package com.example.burgerapp.ui.screens.food_deail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import com.example.burgerapp.data.database.CartDatabase
import com.example.burgerapp.data.repository.CartRepository
import com.example.burgerapp.databinding.FragmentFoodDetailBinding
import com.example.burgerapp.ui.screens.cart.CartViewModelFactory
import com.example.burgerapp.ui.screens.cart.CartViewModel
import kotlinx.coroutines.launch


class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding
    private lateinit var cartViewModel: CartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val fadeTransition = Fade()

        enterTransition = fadeTransition
        exitTransition = fadeTransition

        sharedElementEnterTransition =
            context?.let { TransitionInflater.from(it).inflateTransition(android.R.transition.move) }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartDatabase = CartDatabase.getDatabase(requireContext().applicationContext)
        val cartRepository = CartRepository(cartDatabase.cartDao())

        val factory = CartViewModelFactory(cartRepository)
        cartViewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]

        val imageView = binding.mainImage
        val title = binding.title
        val description = binding.description

        val args: FoodDetailFragmentArgs by navArgs()

        val foodData = args.food

        imageView.setImageResource(args.imageRes)
        imageView.transitionName = "shared_image_${foodData.uuid}"

        title.text = foodData.name
        title.transitionName = "shared_text_${foodData.uuid}"

        description.text = foodData.description


        val counter = cartViewModel.portionsCounter
        val spicyLevel = cartViewModel.spicyLevel

        counter.observe(viewLifecycleOwner) { newCount ->
            binding.portionsCounter.text = newCount.toString()
        }

        binding.addPortion.setOnClickListener {
            cartViewModel.addPortion()
        }

        binding.removePortion.setOnClickListener {
            cartViewModel.removePortion()
        }

        val sliderSpicyLevel = binding.spicySlider

        binding.spicySlider.addOnChangeListener { _, value, _ ->
            cartViewModel.changeSpicyLevel(value)
        }

        spicyLevel.observe(viewLifecycleOwner) { level ->
            sliderSpicyLevel.value = level
        }

        val submitButton = binding.btnAddToCard

        submitButton.setOnClickListener {
            lifecycleScope.launch {
                cartViewModel.addFoodToCart(foodData)
            }
        }
    }

}