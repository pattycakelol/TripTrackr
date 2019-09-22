package com.example.triptrackr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register_firestore.*

class RegisterFirestore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_firestore)

        register_btn_registration.setOnClickListener {
            performRegister()
        }

        register_textview_login.setOnClickListener {
            // Launch LoginFirestore
            startActivity(Intent(this, LoginFirestore::class.java))
        }
    }

    private fun performRegister() {
        val username = register_username.text.toString()
        val email = register_email.text.toString()
        val password = register_password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and/or Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterFirestore", "Email: $email")
        Log.d("RegisterFirestore", "Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                Log.d("RegisterFirestore", "User registered with uid: ${it.result?.user?.uid}")
                Toast.makeText(this, "User registered with uid: ${it.result?.user?.uid}", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Log.d("RegisterFirestore", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
