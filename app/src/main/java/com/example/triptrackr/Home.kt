package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        verifyUserLoggedIn()

        var bottomnNavigationView : BottomNavigationView  = findViewById(R.id.bottomNavViewBar)
        bottomnNavigationView.selectedItemId = R.id.ic_home
        bottomnNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_friends -> {
                    val intent = Intent(this, Friends::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                R.id.ic_home -> {
                    // Do nothing (already on this page)
                }
                R.id.ic_trips -> {
                    val intent = Intent(this, Trips::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

        val btn : Button = findViewById(R.id.track)
        btn.setOnClickListener {
            startActivity(Intent(this@Home, RegisterFirestore::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // Do nothing
    }

    private fun verifyUserLoggedIn() {
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null) {
            val intent = Intent(this, LoginFirestore::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }
}
