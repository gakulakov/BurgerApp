package com.example.burgerapp.ui.screens.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R
import com.example.burgerapp.data.database.CartDatabase
import com.example.burgerapp.data.model.CreditCard
import com.example.burgerapp.data.repository.CartRepository
import com.example.burgerapp.databinding.FragmentCartBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


val MOCK_CREDIT_CARDS = listOf(
    CreditCard(
        id = 0,
        backgroundColorResId = R.color.text_dark,
        name = "MasterCard",
        serialNumber = "5105 **** **** 0505",
        imageRes = R.drawable.master_card,
        isChecked = true,
    ),
    CreditCard(
        id = 0,
        backgroundColorResId = R.color.text_dark,
        name = "MasterCard",
        serialNumber = "5105 **** **** 0505",
        imageRes = R.drawable.master_card,
        isChecked = false,
    ),
)

class CartFragment : Fragment() {
    private var binding: FragmentCartBinding? = null
    private var cartViewModel: CartViewModel? = null

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

        val cartDatabase = CartDatabase.getDatabase(requireContext().applicationContext)
        val cartRepository = CartRepository(cartDatabase.cartDao())

        val factory = CartViewModelFactory(cartRepository)
        cartViewModel = ViewModelProvider(requireActivity(), factory)[CartViewModel::class.java]

        val cartRecyclerView = binding?.rvCartItems
        cartRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        cartRecyclerView?.addItemDecoration(
            CartItemsSpacingVertical(resources.getDimensionPixelSize(R.dimen.margin))
        )

        val cartAdapter = CartAdapter()
        val creditCardAdapter = CreditCardAdapter()


        val concatAdapters = ConcatAdapter(cartAdapter, creditCardAdapter)

        cartRecyclerView?.adapter = concatAdapters
        cartAdapter.onRemoveButtonClick = { cartItem ->
            cartViewModel?.removeFromCart(cartItem)
        }

        cartAdapter.registerAdapterDataObserver(
            object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    cartRecyclerView?.scrollToPosition(0)
                }
            }
        )


        lifecycleScope.launch {
            cartViewModel?.cardFoods?.collect { foodList ->
                val foods = foodList.map { CartAdapter.ListItem.FoodItem(it) }
                val totalPrice = foodList.sumOf {it.price}
                cartAdapter.submitList(
                    listOf(
                        foods,
                        listOf(CartAdapter.ListItem.Price(totalPrice))
                    ).flatten()
                )
                creditCardAdapter.submitList(MOCK_CREDIT_CARDS)
                val formatedTotalPrice = "%.2f".format(totalPrice)
                binding?.totalPrice?.text = "$${formatedTotalPrice}"
            }
        }
    }
}