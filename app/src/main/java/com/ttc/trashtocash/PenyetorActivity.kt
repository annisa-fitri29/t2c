package com.ttc.trashtocash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class PenyetorActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penyetor)

        navController = findNavController(R.id.navHostFragmentPenyetor)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.penyetor_nav)

        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if ( nd.id == R.id.pickUpFragment2 || /*nd.id == R.id.notificationFragment ||*/ nd.id == R.id.akunPenyetorFragment2) {
                bottomNavigation.visibility = View.VISIBLE
            } else {
                bottomNavigation.visibility = View.GONE
            }
        }
    }
}