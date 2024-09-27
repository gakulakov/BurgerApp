package com.example.burgerapp.ui.screens.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.burgerapp.R
import com.example.burgerapp.databinding.FragmentCartBinding
import com.example.burgerapp.ui.screens.home.MOCK_FOODS


class CartFragment : Fragment() {
    private var binding: FragmentCartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding?.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartItemsRecyclerView = binding?.rvCartItems
        cartItemsRecyclerView?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        cartItemsRecyclerView?.addItemDecoration(
            CartItemsSpacingVertical(resources.getDimensionPixelSize(R.dimen.margin))
        )
        val cartItemsAdapter = CartItemAdapter(MOCK_FOODS.subList(0, 5))


        cartItemsRecyclerView?.adapter = cartItemsAdapter
    }
}