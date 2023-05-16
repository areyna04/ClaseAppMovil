package com.example.testloginapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testloginapp.R
import com.example.testloginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the navigation host fragment from this Activity
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        // Instantiate the navController using the NavHostFragment
        navController = navHostFragment.navController
        // Make sure actions in the ActionBar get propagated to the NavController
Log.d("act", navController.toString())
        setSupportActionBar(findViewById(R.id.my_toolbar))
        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun onMyButtonClick(view: View) {
        findNavController(view.id).navigate(R.id.addalbumFragment)
    }

    fun onAlbumesClick(view: View) {
        findNavController(R.id.nav_host_fragment).navigate(R.id.albumFragment)
    }

    fun onArtistasClick(view: View) {
        findNavController(R.id.nav_host_fragment).navigate(R.id.artistFragment)
    }

    fun onColeccionistasClick(view: View) {
        findNavController(R.id.nav_host_fragment).navigate(R.id.collectorFragment)
    }
}