package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var bottomnNavigationView : BottomNavigationView  = findViewById(R.id.bottomNavViewBar)
        bottomnNavigationView.selectedItemId = R.id.ic_home
        bottomnNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_friends -> {
                    startActivity(Intent(this@Home, Friends::class.java))
                }
                R.id.ic_home -> {
                    // Do nothing (already on this page)
                }
                R.id.ic_trips -> {
                    startActivity(Intent(this@Home, Trips::class.java))
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        val btn : Button = findViewById(R.id.track)
        btn.setOnClickListener {
            startActivity(Intent(this@Home, Login::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        // Do nothing
    }
}
