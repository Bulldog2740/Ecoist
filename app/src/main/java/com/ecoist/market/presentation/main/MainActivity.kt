package com.ecoist.market.presentation.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.DataBase
import com.ecoist.market.domain.analytics.AppLogger
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppLogger.debug(TAG, "onCreate()")
        val navHostFr = findNavController(R.id.navHostFragment)
        val bottom = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navHostFr.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.splashFragment) {
                bottom.visibility = View.INVISIBLE
            } else {
                bottom.visibility = View.VISIBLE
            }

        }
        bottom.setupWithNavController(navHostFr)
    }
}


