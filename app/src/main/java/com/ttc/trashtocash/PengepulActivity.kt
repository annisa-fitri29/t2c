package com.ttc.trashtocash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class PengepulActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengepul)

        navController = findNavController(R.id.navHostFragmentProdukPengepul)
        val bottomNavigationPengepul = findViewById<BottomNavigationView>(R.id.pengepulNav)

        bottomNavigationPengepul.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.viewPagerPengFragment2 || nd.id == R.id.akunPengepulFragment2 ) {
                bottomNavigationPengepul.visibility = View.VISIBLE
            } else {
                bottomNavigationPengepul.visibility = View.GONE
            }
        }
    }
}