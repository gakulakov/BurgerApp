package com.example.burgerapp

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.burgerapp.databinding.FragmentHeaderBinding

class HeaderFragment : Fragment() {
    private var binding: FragmentHeaderBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHeaderBinding.inflate(inflater, container, false)
        val view = binding?.root

        val navController = findNavController()
        val mainActivity = activity as AppCompatActivity
        val toolbar = binding?.toolbar

        mainActivity.setSupportActionBar(toolbar)
        toolbar?.setupWithNavController(findNavController())
        toolbar?.setNavigationIcon(R.drawable.arrow_left)

        navController.addOnDestinationChangedListener { _, _, _ ->
            mainActivity.supportActionBar?.title = ""
        }

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.appbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        Log.d("MyLog", "Press on Search")
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        val mainActivity = activity as AppCompatActivity
        mainActivity.supportActionBar?.hide()
    }


    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}