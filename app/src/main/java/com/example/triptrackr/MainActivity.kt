package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val friendsButton : Button = findViewById(R.id.view_friends) as Button
        val tripsButton : Button = findViewById(R.id.view_trips) as Button

        friendsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, Friends::class.java))
        }
        tripsButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, Trips::class.java))
        }
    }
}


