package com.example.burgerapp.ui.screens.chat

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.burgerapp.data.model.Message
import com.example.burgerapp.databinding.AdminMessageItemBinding
import com.example.burgerapp.databinding.UserMessageItemBinding

class ChatAdapter: ListAdapter<Message, RecyclerView.ViewHolder>(ChatDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).username) {
            "ADMIN" -> 1
            else -> 0
        }
    }

    inner class UserMessageViewHolder(private val itemBinding: UserMessageItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(message: Message) {
            itemBinding.message.text = message.text
        }
    }

    inner class AdminMessageViewHolder(private val itemBinding: AdminMessageItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(message: Message) {
            itemBinding.message.text = message.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val itemBinding = AdminMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AdminMessageViewHolder(itemBinding)
            }
            else -> {
                val itemBinding = UserMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                UserMessageViewHolder(itemBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItem(position).username) {
            "ADMIN" -> (holder as AdminMessageViewHolder).bind(getItem(position))
            else -> (holder as UserMessageViewHolder).bind(getItem(position))
        }
    }

    class ChatDiffCallBack : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }
}

class ChatItemsSpacingVertical(private val spaceSize: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)

            if (position == state.itemCount - 1) {
                return
            }

            if (position == 0) {
                bottom = 640
            }
                top = spaceSize
        }
    }
}