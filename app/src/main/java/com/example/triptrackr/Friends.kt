package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class Friends : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        var bottomnNavigationView : BottomNavigationView = findViewById(R.id.bottomNavViewBar)
        bottomnNavigationView.selectedItemId = R.id.ic_friends
        bottomnNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_friends -> {
                    // Do nothing (already on this page)
                }
                R.id.ic_home -> {
                    startActivity(Intent(this@Friends, Home::class.java))
                }
                R.id.ic_trips -> {
                    startActivity(Intent(this@Friends, Trips::class.java))
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        startActivity(Intent(this@Friends, Home::class.java))
    }
}
