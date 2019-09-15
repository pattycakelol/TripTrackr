package com.example.triptrackr

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    val friends_button : Button = findViewById(R.id.view_friends);

    friends_button.

//    friends_button.setOnClickListener (object: View.OnClickListener {
//        override fun onClick(view: View): Unit {
//            startActivity(Intent(this, Friends::class.java))
//        }
//
//        private fun Intent(onClickListener: View.OnClickListener, java: Class<Friends>): Context {
//
//        }
//    })
}