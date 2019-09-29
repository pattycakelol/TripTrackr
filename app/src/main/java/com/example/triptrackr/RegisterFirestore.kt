package com.example.triptrackr

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.ImageDecoder
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register_firestore.*
import java.util.*

class RegisterFirestore : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_firestore)

        register_btn_photo.setOnClickListener {
            Log.d("RegisterFirestore", "Photo is being selected")

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }

        register_textview_login.setOnClickListener {
            // Launch LoginFirestore
            val intent = Intent(this, LoginFirestore::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        register_btn_registration.setOnClickListener {
            performRegister()
        }
    }

    var selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            // check what the image was
            Log.d("RegisterFirestore", "Photo was selected")

            selectedPhotoUri = data.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            register_imageview_photo.setImageBitmap(bitmap)

            register_btn_photo.alpha = 0f
        }
    }

    private fun performRegister() {
//        val username = register_username.text.toString()
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

                uploadImageToFirebase()
            }
            .addOnFailureListener {
                Log.d("RegisterFirestore", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun uploadImageToFirebase() {
        if (selectedPhotoUri == null) {
            Log.d("RegisterFirestore", "No image uploaded")
            saveUserToFirebaseDatabase("[no image]")
            return
        }

        val filename = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("/images/$filename")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("RegisterFirestore", "Successfully uploaded image: ${it.metadata?.path}")

                ref.downloadUrl.addOnSuccessListener {
                    Log.d("RegisterFirestore", "File Location: $it")
                    saveUserToFirebaseDatabase(it.toString())
                }
            }
            .addOnFailureListener {
                Log.d("RegisterFirestore", "Image unable to be uploaded to Firebase storage")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageUrl: String) {
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().reference

        Log.d("RegisterFirestore", "Image url received: $profileImageUrl")

        ref.child("users").child(uid).setValue(User(register_username.text.toString(), profileImageUrl))
            .addOnSuccessListener {
                Log.d("RegisterFirestore", "User created in Firebase database")

                val intent = Intent(this, Home::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .addOnFailureListener {
                Log.d("RegisterFirestore", "User unable to be created in Firebase database")
            }
    }

    override fun onBackPressed() {
        val intent = Intent(this, LoginFirestore::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}

data class User(val username: String, val profileImageUrl: String) {
    constructor() : this("", "")
}