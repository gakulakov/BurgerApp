package com.example.burgerapp.ui.screens.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.model.CreditCard
import com.example.burgerapp.databinding.CheckboxLayoutBinding
import com.example.burgerapp.databinding.CreditCardItemBinding

class CreditCardAdapter : ListAdapter<CreditCard, RecyclerView.ViewHolder>(DiffCallback()) {
    private enum class TYPES_OF_VIEW {
        CARD,
        CHECKBOX
    }



    override fun getItemViewType(position: Int): Int {
        return when {
            position < currentList.size -> TYPES_OF_VIEW.CARD.ordinal
            else -> TYPES_OF_VIEW.CHECKBOX.ordinal
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {


        return when (viewType) {
            TYPES_OF_VIEW.CARD.ordinal -> {
                val itemBinding = CreditCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CreditCardViewHolder(itemBinding)
            }
            else -> {
                val itemBinding = CheckboxLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return CheckboxViewHolder(itemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            position < currentList.size -> (holder as CreditCardViewHolder).bind(getItem(position))
            else -> {
                (holder as CheckboxViewHolder).bind(false)
            }
        }

    }

    class DiffCallback : DiffUtil.ItemCallback<CreditCard>() {
        override fun areItemsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CreditCard, newItem: CreditCard): Boolean {
            return oldItem == newItem
        }

    }

    override fun getItemCount() = currentList.size + 1

    inner class CreditCardViewHolder(private val itemBinding: CreditCardItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val mContext: Context = itemBinding.root.context

        fun bind(card: CreditCard) {
            itemBinding.container.setCardBackgroundColor(
                ContextCompat.getColor(mContext, card.backgroundColorResId)
            )
            itemBinding.image.setImageResource(card.imageRes)
            itemBinding.cardLabel.text = card.name
            itemBinding.radio.isChecked = card.isChecked
        }
    }

    inner class CheckboxViewHolder(private val itemBinding: CheckboxLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(isChecked: Boolean) {
            itemBinding.checkbox.isChecked = isChecked
            itemBinding.checkboxLabel.setOnClickListener {
                itemBinding.checkbox.isChecked = !itemBinding.checkbox.isChecked
            }
        }
    }
}