package com.example.triptrackr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class Trips_user : Fragment() {
    lateinit var btnNavTripsUser : Button
    lateinit var btnNavTripsFriends : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_trips_user, container, false)
        btnNavTripsUser = view.findViewById(R.id.btnNavTripsUser) as Button
        btnNavTripsFriends = view.findViewById(R.id.btnNavTripsFriends) as Button
        Log.d("Trips_user", "onCreateView: started")

        btnNavTripsUser.setOnClickListener {
            Toast.makeText(activity, "Going to fragment_trips_user", Toast.LENGTH_SHORT).show()
            (activity as Trips).setViewPager(0)
        }
        btnNavTripsFriends.setOnClickListener {
            Toast.makeText(activity, "Going to fragment_trips_friends", Toast.LENGTH_SHORT).show()
            (activity as Trips).setViewPager(1)
        }

        return view
    }
}