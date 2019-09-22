package com.example.triptrackr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_firestore.*

class LoginFirestore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_firestore)

        login_btn_login.setOnClickListener {
            performLogin()
        }

        login_textview_register.setOnClickListener {
            finish()
        }
    }

    private fun performLogin() {
        val email = login_email.text.toString()
        val password = login_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and/or Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("LoginFirestore", "Email: $email")
        Log.d("LoginFirestore", "Password: $password")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                Log.d("LoginFirestore", "User logged in with uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "User logged in with uid: ${it.result?.user?.uid}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("LoginFirestore", "Failed to login user: ${it.message}")
                Toast.makeText(this, "Failed to login user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
