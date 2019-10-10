package com.example.triptrackr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_friend_view.*

class Friend_view : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_view)

        val user = intent.getParcelableExtra<User>(Friends.USER_KEY)
        friend_view_username.text = user.username
        Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.friend_view_icon)

        friend_view_back.setOnClickListener {
            finish()
        }
    }
}
