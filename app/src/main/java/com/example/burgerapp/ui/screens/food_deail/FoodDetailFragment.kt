package com.example.burgerapp.ui.screens.food_deail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import com.example.burgerapp.databinding.FragmentFoodDetailBinding


class FoodDetailFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailBinding

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


        val imageView = binding.mainImage
        val title = binding.title
        val description = binding.description

        val args: FoodDetailFragmentArgs by navArgs()

        val foodData = args.food

        imageView.setImageResource(args.imageRes)
        imageView.transitionName = "shared_image_${foodData.id}"

        title.text = foodData.name
        title.transitionName = "shared_text_${foodData.id}"

        description.text = foodData.description

        val foodDetailViewModel: FoodDetailViewModel by viewModels()

        val counter = foodDetailViewModel.portionsCounter
        val spicyLevel = foodDetailViewModel.spicyLevel

        counter.observe(viewLifecycleOwner) { newCount ->
            binding.portionsCounter.text = newCount.toString()
        }

        binding.addPortion.setOnClickListener {
            foodDetailViewModel.addPortion()
        }

        binding.removePortion.setOnClickListener {
            foodDetailViewModel.removePortion()
        }

        val sliderSpicyLevel = binding.spicySlider

        binding.spicySlider.addOnChangeListener { _, value, _ ->
            foodDetailViewModel.changeSpicyLevel(value)
        }

        spicyLevel.observe(viewLifecycleOwner) { level ->
            sliderSpicyLevel.value = level
        }
    }

}