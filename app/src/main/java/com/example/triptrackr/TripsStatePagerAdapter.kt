package com.example.triptrackr

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class TripsStatePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {


    val fragmentList : MutableList<Fragment> = ArrayList<Fragment>()
    val fragmentTitleList : MutableList<String> = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        fragmentTitleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

}