package com.example.burgerapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.burgerapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = true

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        val bottomNavigationView = binding.coordinator.bottomNavigationView
        val fab = binding.coordinator.fab

        fab.setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY)

        bottomNavigationView.setBackgroundColor(Color.TRANSPARENT)
        bottomNavigationView.menu.getItem(2).isEnabled = false

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        fab.setOnClickListener {
            navController.navigate(R.id.cart)
        }


        lifecycleScope.launch {
            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.food_details, R.id.cart -> {
                        fab.visibility = View.GONE
                        bottomNavigationView.visibility = View.GONE
                    }
                    else -> {
                        fab.visibility = View.VISIBLE
                        bottomNavigationView.visibility = View.VISIBLE
                    }
                }
            }
        }

    }
}