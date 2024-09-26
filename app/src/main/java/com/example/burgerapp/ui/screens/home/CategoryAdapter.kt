package com.example.burgerapp.ui.screens.home

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.R
import com.example.burgerapp.databinding.CategoryListItemBinding

class CategoryAdapter(private val categories: List<String>, private var activeButtonPosition: Int) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // Функция onClick
    var onItemClick: ((Int) -> Unit)? = null

    // Связывание XML с RecyclerView и логика наполнение данными элемента списка
    inner class ViewHolder(private val itemBinding: CategoryListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val context = itemBinding.root.context
        fun bind(category: String) {
            itemBinding.categoryListItem.text = category
            itemBinding.cardView.setOnClickListener {
                onItemClick?.invoke(adapterPosition)
            }
        }

        fun changeActive(isActive: Boolean) {
            itemBinding
                .cardView
                .setCardBackgroundColor(
                    if (isActive)
                        ContextCompat.getColor(context, R.color.red_navigation)
                    else
                        ContextCompat.getColor(context, R.color.light_grey)
                )
            itemBinding
                .categoryListItem
                .setTextColor(
                    if (isActive)
                        ContextCompat.getColor(context, R.color.white)
                    else
                        ContextCompat.getColor(context, R.color.grey)
                )

            itemBinding
                .cardView
                .cardElevation = if (isActive) 20f else 0f
        }
    }

    // Определение ViewHolder и возврат элемента, который используется для отрисовки в RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            CategoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(itemBinding)
    }

    // Получение переданного ViewHolder из onCreateViewHolder и передача данных во внутрь ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])

        holder.changeActive(position == activeButtonPosition)
    }

    // Определение размера списка
    override fun getItemCount() = categories.size

    fun setActiveButton(value: Int) {
        val previuslyActiveButton = activeButtonPosition
        activeButtonPosition = value

        notifyItemChanged(previuslyActiveButton)
        notifyItemChanged(activeButtonPosition)
    }
}


// Spacing между элементами списка
class CategorySpacingHorizontal(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                return
            }
            right = spaceSize

        }
    }
}