package com.example.burgerapp.ui.screens.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.model.Food
import com.example.burgerapp.databinding.FavoriteItemBinding

class FavoriteAdapter : ListAdapter<Food, FavoriteAdapter.FavoriteViewHolder>(FavoriteDiffCallBack()) {
    var onButtonClick: ((Food) -> Unit)? = null

    inner class FavoriteViewHolder(private val itemBinding: FavoriteItemBinding): RecyclerView.ViewHolder(itemBinding.root){
        fun bind(food: Food) {
            itemBinding.image.setImageResource(food.imageRes)
            itemBinding.name.text = "${food.name}\n${food.subtitle}"
            itemBinding.price.text = food.price.toString()

            itemBinding.removeButton.setOnClickListener {
                onButtonClick?.invoke(food)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        val itemBinding = FavoriteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FavoriteDiffCallBack : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }

    }
}