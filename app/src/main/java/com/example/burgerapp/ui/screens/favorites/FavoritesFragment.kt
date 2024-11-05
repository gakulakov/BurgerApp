package com.example.burgerapp.ui.screens.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burgerapp.R
import com.example.burgerapp.databinding.FragmentFavoritesBinding
import com.example.burgerapp.ui.screens.cart.CartItemsSpacingVertical
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {
    private var binding: FragmentFavoritesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        val view = binding?.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(v.paddingLeft, statusBarHeight.top, v.paddingRight, 0)

            insets
        }

        val navController = findNavController()
        val favoriteViewModel: FavoriteViewModel by viewModels()

        binding?.redirectBtn?.setOnClickListener {
            navController.popBackStack()
        }

        val emptyContainer = binding?.emptyContainer

        val rvFavorites = binding?.rvFavorites
        val adapter = FavoriteAdapter()

        adapter.onButtonClick = {
            favoriteViewModel.removeFavoriteFood(it)
        }
        rvFavorites?.adapter = adapter
        rvFavorites?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvFavorites?.addItemDecoration(
            CartItemsSpacingVertical(resources.getDimensionPixelSize(R.dimen.margin))
        )



        lifecycleScope.launch {
            favoriteViewModel.foods.collect {
                if (it.isEmpty()) {
                    rvFavorites?.visibility = View.GONE
                    emptyContainer?.visibility = View.VISIBLE
                } else {
                    rvFavorites?.visibility = View.VISIBLE
                    emptyContainer?.visibility = View.GONE
                }
                adapter.submitList(it)
            }
        }
    }

}