package com.example.burgerapp.ui.screens.cart

import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.model.Food
import com.example.burgerapp.databinding.CartItemBinding
import com.example.burgerapp.databinding.PricingCartLayoutBinding

class CartAdapter: ListAdapter<CartAdapter.ListItem, RecyclerView.ViewHolder>(DiffCallback()) {
    sealed class ListItem {
        data class FoodItem(val food: Food): ListItem()
        data class Price(val price: Double): ListItem()
    }

    private enum class TYPES_CART_ITEMS {
        FOOD,
        PRICE,
    }

    var onRemoveButtonClick: ((Food) -> Unit)? = null

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ListItem.FoodItem -> TYPES_CART_ITEMS.FOOD.ordinal
            is ListItem.Price -> TYPES_CART_ITEMS.PRICE.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemType = TYPES_CART_ITEMS.entries[viewType]

        return when (itemType) {
            TYPES_CART_ITEMS.FOOD -> {
                val itemBinding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return FoodViewHolder(itemBinding)
            }
            TYPES_CART_ITEMS.PRICE -> {
                val itemBinding = PricingCartLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return PriceViewHolder(itemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is ListItem.FoodItem -> (holder as FoodViewHolder).bind(item.food)
            is ListItem.Price -> (holder as PriceViewHolder).bind(item.price)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ListItem>() {
        override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {

            return when {
                oldItem is ListItem.FoodItem && newItem is ListItem.FoodItem -> oldItem.food.id == newItem.food.id
                oldItem is ListItem.Price && newItem is ListItem.Price -> true
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class FoodViewHolder(private val itemBinding: CartItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(food: Food) {
            itemBinding.image.setImageResource(food.imageRes)
            itemBinding.name.text = "${food.name}\n${food.subtitle}"
            itemBinding.price.text = "$${food.price}"

            itemBinding.removeButton.setOnClickListener {
                onRemoveButtonClick?.invoke(food)
            }
        }
    }

    inner class PriceViewHolder(private val itemBinding: PricingCartLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(price: Double) {
            val formatedPrice = "%.2f".format(price)
            val formatedOrder = "%.2f".format((price / 100.0) * 90.6)
            val formatedTax = "%.2f".format((price / 100.0) * 1.65)
            val formatedDeliveryFees = "%.2f".format((price / 100.0) * 7.75)
            itemBinding.order.text = "$$formatedOrder"
            itemBinding.taxes.text = "$$formatedTax"
            itemBinding.deliveryFees.text = "$$formatedDeliveryFees"
            itemBinding.total.text = "$$formatedPrice"
                // order = 90.6
            // tax = 1.65
            // Delivery fees = 8.25
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