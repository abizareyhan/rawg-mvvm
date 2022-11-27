package com.abizareyhan.game.view.activity

import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.abizareyhan.core.base.BaseActivity
import com.abizareyhan.playground.home.R
import com.abizareyhan.playground.home.databinding.ActivityGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameActivity: BaseActivity<ActivityGameBinding>(
    ActivityGameBinding::inflate
) {
    override fun init() {
        setupBottomNavigationView()
    }

    private fun setupBottomNavigationView() {
        with(binding) {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when(destination.id) {
                    R.id.homeFragment, R.id.favoriteFragment -> {
                        bottomNavigationView.visibility = View.VISIBLE
                    }
                    else -> {
                        bottomNavigationView.visibility = View.GONE
                    }
                }
            }

            bottomNavigationView.setupWithNavController(navController)
        }
    }
}