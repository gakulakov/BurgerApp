package com.example.burgerapp.ui.screens.home

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.burgerapp.R
import com.example.burgerapp.data.Food
import com.example.burgerapp.databinding.FoodListItemBinding
import kotlinx.coroutines.coroutineScope


class FoodAdapter(): RecyclerView.Adapter<FoodAdapter.FoodHolder>() {
    var onItemClick: ((textView: View, imageView: View, foodItem: Food) -> Unit)? = null
    var onFavoriteClick: ((Food) -> Unit)? = null
    private lateinit var data: MutableList<Food>

    inner class FoodHolder(private val itemBinding: FoodListItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        val context: Context = itemBinding.root.context
        fun bind(food: Food) {
            // Добавление уникального имени для Shared Element Transition
            itemBinding.foodImage.transitionName = "shared_image_${food.id}"
            itemBinding.foodText.transitionName = "shared_text_${food.id}"
            itemBinding.foodItem.setOnClickListener {
                with(itemBinding) {
                    // Сам ImageView для обозначения элемента перехода и объект Food для передачи imageRes (пока что только imageRes)
                    onItemClick?.invoke(foodText, foodImage, food)
                }

            }


            itemBinding.foodText.text = food.name
            itemBinding.foodDescription.text = food.subtitle
            itemBinding.ratingText.text = food.rating.toString()
            itemBinding.foodImage.setImageResource(food.imageRes)

            itemBinding.favoriteIcon.setOnClickListener {
                onFavoriteClick?.invoke(food)
            }

            if (food.isFavorite) {
                itemBinding.favoriteIcon.setColorFilter(ContextCompat.getColor(context, R.color.red_navigation), PorterDuff.Mode.SRC_IN)
                itemBinding.favoriteIcon.setImageResource(R.drawable.heart_filled)
            } else {
                itemBinding.favoriteIcon.setImageResource(R.drawable.heart_outline)
                itemBinding.favoriteIcon.setColorFilter(ContextCompat.getColor(context, R.color.text_dark), PorterDuff.Mode.SRC_IN)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val itemBinding = FoodListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return FoodHolder(itemBinding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(foodList: List<Food>) {
        this.data = foodList.toMutableList()
    }

    // Использование DiffUtil
    fun setNewData(newFoodList: List<Food>) {
        val diffCallback = FoodsCallback(data, newFoodList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        data.clear()
        data.addAll(newFoodList)
        diffResult.dispatchUpdatesTo(this)
    }

}

// TODO: Попробовать использовать DiffUtil с созданием внутри RecyclerView.Adapter (с помощью AsyncDiffer ??? )

// DiffUtil для калькуляции изменений в списке внутри RecyclerView
class FoodsCallback(private val oldList: List<Food>, private val newList: List<Food>): DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.hashCode() == newItem.hashCode()
    }

}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left =
                spacing - column * spacing / spanCount
            outRect.right =
                (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right =
                spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}
