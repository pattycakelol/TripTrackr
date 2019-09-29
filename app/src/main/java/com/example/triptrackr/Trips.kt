package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout

class Trips : AppCompatActivity() {

    lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)
        Log.d("Trips", "onCreate: started")

        mViewPager = findViewById(R.id.container) as ViewPager
        setupViewPager(mViewPager)

        var tabLayout : TabLayout = findViewById(R.id.tabs) as TabLayout
        tabLayout.setupWithViewPager(mViewPager)

        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_trips_user)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_trips_friends)

        var bottomnNavigationView : BottomNavigationView  = findViewById(R.id.bottomNavViewBar)
        bottomnNavigationView.selectedItemId = R.id.ic_trips
        bottomnNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_friends -> {
                    val intent = Intent(this, Friends::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                R.id.ic_home -> {
                    val intent = Intent(this, Home::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                R.id.ic_trips -> {
                    // Do nothing (already on this page)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onBackPressed() {
        val intent = Intent(this, Home::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    fun setupViewPager(viewPager : ViewPager) {
        var adapter : TripsStatePagerAdapter = TripsStatePagerAdapter(supportFragmentManager)
        adapter.addFragment(Trips_user(), "Trips_user")
        adapter.addFragment(Trips_friends(), "Trips_friends")
        viewPager.adapter = adapter
    }

    fun setViewPager(fragmentNumber: Int) {
        mViewPager.setCurrentItem(fragmentNumber)
    }
}
