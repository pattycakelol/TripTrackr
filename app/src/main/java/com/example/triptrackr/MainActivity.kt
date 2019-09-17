package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var bottomnNavigationView : BottomNavigationView = findViewById(R.id.bottomNavViewBar)
        bottomnNavigationView.selectedItemId = R.id.ic_home
        bottomnNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_friends -> {
                    startActivity(Intent(this@MainActivity, Friends::class.java))
                }
                R.id.ic_home -> {
                    // Do nothing (already on this page)
                }
                R.id.ic_trips -> {
                    startActivity(Intent(this@MainActivity, Trips::class.java))
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}


