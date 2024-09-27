package com.example.burgerapp.ui.screens.cart

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.Food
import com.example.burgerapp.databinding.CartItemBinding

class CartItemAdapter(initialCartItems: List<Food>): ListAdapter<Food, CartItemAdapter.ViewHolder>(DiffCallback()) {
    private var cartItems: MutableList<Food> = initialCartItems.toMutableList()

    fun setCartItems(newCartItems: List<Food>) {
        cartItems = newCartItems.toMutableList()
    }

    inner class ViewHolder(private val itemBinding: CartItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(food: Food) {
            itemBinding.image.setImageResource(food.imageRes)
            itemBinding.name.text = "${food.name}\n${food.subtitle}"
            itemBinding.price.text = "$${food.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemAdapter.ViewHolder {
        val itemBinding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CartItemAdapter.ViewHolder, position: Int) {
        cartItems.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount() = cartItems.size

    class DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }
    }
}

class CartItemsSpacingVertical(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                return
            }
            bottom = spaceSize

        }
    }
}