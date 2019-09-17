package com.example.triptrackr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
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

        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_user)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_friends)
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
