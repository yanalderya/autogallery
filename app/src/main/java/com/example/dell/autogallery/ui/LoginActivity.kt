package com.example.dell.autogallery.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dell.autogallery.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val edtMail: EditText by lazy { findViewById<EditText>(R.id.activity_register_edtMail) }
    private val edtSifre: EditText by lazy { findViewById<EditText>(R.id.activity_login_edtPassword) }
    private val btnSignin: Button by lazy { findViewById<Button>(R.id.activity_login_btnSingin) }
    private val txtSignup: TextView by lazy { findViewById<TextView>(R.id.activity_login_txtSingUp) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initEvent()
    }

    private fun initEvent() {

        btnSignin.setOnClickListener {
            if (edtMail.text.isNotEmpty() && edtSifre.text.isNotEmpty()) {

                progressBarGoster()
                if (edtMail.text.toString() == "admin" && edtSifre.text.toString() == "123") {
                    val intent = Intent(this, DashboardActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(edtMail.text.toString(), edtSifre.text.toString())
                            .addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                                override fun onComplete(p0: Task<AuthResult>) {

                                    if (p0.isSuccessful) {
                                        progressBarGizle()

                                        val intent=Intent(this@LoginActivity,GuestActivity::class.java)
                                        startActivity(intent)
                                        finish()

                                    } else {
                                        progressBarGizle()
                                        Toast.makeText(this@LoginActivity, "Hatalı Giriş" + p0.exception?.message, Toast.LENGTH_SHORT).show()
                                    }
                                }

                            })
                }
            } else {
                progressBarGizle()
                Toast.makeText(this, "Boş Alanları Doldurunuz ", Toast.LENGTH_SHORT).show()
            }
        }

        txtSignup.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun progressBarGoster() {
        progressBar.visibility = View.VISIBLE
    }

    private fun progressBarGizle() {
        progressBar.visibility = View.GONE
    }

}
