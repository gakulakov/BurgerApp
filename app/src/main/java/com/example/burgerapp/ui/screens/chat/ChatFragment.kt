package com.example.burgerapp.ui.screens.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsAnimationCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.burgerapp.R
import com.example.burgerapp.data.model.Message
import com.example.burgerapp.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        val constraintLayout = binding.mainConstraint


        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, statusBarHeight.top, 0, 0)
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())

            ViewCompat.setWindowInsetsAnimationCallback(
                view,
                object : WindowInsetsAnimationCompat.Callback(DISPATCH_MODE_STOP) {
                    override fun onProgress(
                        insets: WindowInsetsCompat,
                        runningAnimations: MutableList<WindowInsetsAnimationCompat>
                    ): WindowInsetsCompat {
                        val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                        val sysBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())

                        val extraOffset = if (imeVisible) 200 else 0
                        val updatedHeight = if(imeHeight - sysBarInsets.bottom < 0) 0 else imeHeight - sysBarInsets.bottom - extraOffset


                        constraintLayout.translationY = -updatedHeight.toFloat()
                        return insets
                    }
                }
            )

            insets
        }





        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvMessages = binding.rvMessages

        rvMessages.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)

        val chatAdapter = ChatAdapter()
        rvMessages.adapter = chatAdapter
        rvMessages.addItemDecoration(
            ChatItemsSpacingVertical(resources.getDimensionPixelSize(R.dimen.margin))
        )

        val messages = listOf(
            Message(
                id = 1,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 2,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 3,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 4,
                text = "Hello World!",
                username = "ADMIN"
            ),
            Message(
                id = 5,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 5,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 6,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 7,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 8,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 9,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 10,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 11,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 12,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 13,
                text = "Hello World!",
                username = "Lena"
            ),
            Message(
                id = 14,
                text = "Hello World!",
                username = "Lena"
            ),
        )

        chatAdapter.submitList(messages)

//        val rootView = requireView()
//        rootView.viewTreeObserver.addOnGlobalLayoutListener {
//            val rect = Rect()
//            rootView.getWindowVisibleDisplayFrame(rect)
//            val screenHeight = rootView.rootView.height
//            val keypadHeight = screenHeight - rect.bottom
//
//            // Проверяем, если клавиатура занимает больше 15% экрана
//            if (keypadHeight > screenHeight * 0.15) {
//                // Поднимаем `CoordinatorLayout` на высоту клавиатуры
//                rootView.translationY = -keypadHeight.toFloat()
//            } else {
//                // Возвращаем `CoordinatorLayout` на исходное место
//                rootView.translationY = 0f
//            }
//        }
    }

}