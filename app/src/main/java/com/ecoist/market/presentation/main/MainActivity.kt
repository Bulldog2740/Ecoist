package com.ecoist.market.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.DataBase
import com.ecoist.market.domain.analytics.AppLogger
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.navHostFragment)
        val bottomBar = findViewById<BottomNavigationView>(R.id.bottom_nav)
        listenDestinations(navController, bottomBar)
        bottomBar.setupWithNavController(navController)
    }

    private fun listenDestinations(
        navController: NavController,
        bottomBar: BottomNavigationView
    ) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                bottomBar.visibility = View.INVISIBLE
            } else {
                bottomBar.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val TAG: String = "MainActivity"
    }
}


