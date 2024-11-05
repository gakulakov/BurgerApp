package com.example.burgerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.burgerapp.databinding.FragmentHeaderBinding

class HeaderFragment : Fragment() {
    private var binding: FragmentHeaderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHeaderBinding.inflate(inflater, container, false)
        val view = binding?.root

        val navController = findNavController()
        val mainActivity = activity as AppCompatActivity
        val toolbar = binding?.toolbar

        mainActivity.setSupportActionBar(toolbar)
        toolbar?.setupWithNavController(findNavController())
        toolbar?.setNavigationIcon(R.drawable.arrow_left)
        mainActivity.supportActionBar?.title = ""

        requireActivity().addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                val isPerson = navController.currentDestination?.label == "Person"
                menuInflater.inflate(R.menu.appbar_menu, menu)

                if (isPerson) {
                    val iconView = menu.findItem(R.id.action_search)
                    iconView.apply {
                        setIcon(R.drawable.ic_settings_foreground)
                    }
                }

            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        true
                    }

                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}