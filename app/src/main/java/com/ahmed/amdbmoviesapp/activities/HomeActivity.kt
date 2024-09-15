package com.ahmed.amdbmoviesapp.activities

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.ahmed.amdbmoviesapp.R
import com.google.android.material.chip.ChipGroup
import com.google.android.material.chip.Chip
import com.ismaeldivita.chipnavigation.ChipNavigationBar


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var menu_bottom = findViewById<ChipNavigationBar>(R.id.bottom_nav_bar)
        menu_bottom.setItemSelected(R.id.nav_explore)

        menu_bottom.setOnItemSelectedListener {
            when (it) {

                R.id.nav_explore -> {
                    findNavController(R.id.nav_host_home).navigate(R.id.homeFragment)
                }
                R.id.nav_search -> {
                    findNavController(R.id.nav_host_home).navigate(R.id.detailsFragment)
                }
                R.id.nav_profile -> {
                    findNavController(R.id.nav_host_home).navigate(R.id.profileFragment)
                }
            }
        }


    }
}