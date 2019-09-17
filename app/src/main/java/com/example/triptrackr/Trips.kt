package com.example.triptrackr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager

class Trips : AppCompatActivity() {

    lateinit var mViewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trips)
        Log.d("Trips", "onCreate: started")

        mViewPager = findViewById(R.id.container) as ViewPager
        setupViewPager(mViewPager)
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
