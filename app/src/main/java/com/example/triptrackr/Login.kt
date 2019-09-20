package com.example.triptrackr

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.example.triptrackr.Retrofit.IMyService
import com.example.triptrackr.Retrofit.RetrofitClient
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog
import com.rengwuxian.materialedittext.MaterialEditText
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    lateinit var iMyService : IMyService
    internal var compositeDisposable = CompositeDisposable()

    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Init API
        val retrofit = RetrofitClient.getInstance()
        iMyService = retrofit.create(IMyService::class.java)

        // event
        btn_login.setOnClickListener {
            loginUser(input_id_login.text.toString(), input_password_login.text.toString())
        }

        btn_register.setOnClickListener {
            val itemView = LayoutInflater.from(this@Login)
                .inflate(R.layout.register_layout, null)

            MaterialStyledDialog.Builder(this@Login)
                .setIcon(R.drawable.ic_user)
                .setTitle("Registration")
                .setDescription("Please fill all fields")
                .setCustomView(itemView)
                .setNegativeText("Cancel")
                .onNegative {dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveText("Register")
                .onPositive (
                    MaterialDialog.SingleButtonCallback { _, _ ->
                        val id = itemView.findViewById<View>(R.id.input_id_register) as MaterialEditText
                        val name = itemView.findViewById<View>(R.id.input_name_register) as MaterialEditText
                        val password = itemView.findViewById<View>(R.id.input_password_register) as MaterialEditText

                        if (TextUtils.isEmpty(id.text.toString())) {
                            Toast.makeText(this@Login, "ID cannot be empty", Toast.LENGTH_SHORT).show()
                            return@SingleButtonCallback
                        }
                        if (TextUtils.isEmpty(name.text.toString())) {
                            Toast.makeText(this@Login, "Name cannot be empty", Toast.LENGTH_SHORT).show()
                            return@SingleButtonCallback
                        }
                        if (TextUtils.isEmpty(password.text.toString())) {
                            Toast.makeText(this@Login, "Password cannot be empty", Toast.LENGTH_SHORT).show()
                            return@SingleButtonCallback
                        }

                        registerUser(id.text.toString(), name.text.toString(), password.text.toString())
                    })
                .show()
        }
    }

    private fun registerUser(id: String, name: String, password: String) {
        compositeDisposable.addAll(iMyService.registerUser(id, name, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe { result ->
            Toast.makeText(this@Login, ""+result, Toast.LENGTH_SHORT).show()
        })
    }

    private fun loginUser (id: String, password: String) {

        // Check empty
        if (TextUtils.isEmpty(id)) {
            Toast.makeText(this@Login, "ID cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this@Login, "Password cannot be empty", Toast.LENGTH_SHORT).show()
            return
        }

        compositeDisposable.addAll(iMyService.loginUser(id, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { result ->
                Toast.makeText(this@Login, ""+result, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Login, Friends::class.java))
            }
        )

    }
}
