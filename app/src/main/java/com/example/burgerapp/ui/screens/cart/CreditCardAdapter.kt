package com.example.burgerapp.ui.screens.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.CreditCard
import com.example.burgerapp.databinding.CreditCardItemBinding

class CreditCardAdapter : ListAdapter<CreditCard, CreditCardAdapter.ViewHolder>(DiffCallback()) {
    private var cards: MutableList<CreditCard>? = null

    fun setCards(newCards: List<CreditCard>) {
        cards = newCards.toMutableList()
    }

    inner class ViewHolder(private val itemBinding: CreditCardItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind() {

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CreditCardAdapter.ViewHolder {
        val itemBinding = CreditCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CreditCardAdapter.ViewHolder, position: Int) {

    }

    override fun getItemCount() = cards?.size ?: 0

    class DiffCallback : DiffUtil.ItemCallback<CreditCard>() {
        override fun areItemsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean {
            return oldItem == newItem
        }

    }
}