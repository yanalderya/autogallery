package com.example.dell.autogallery.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.dell.autogallery.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreen : AppCompatActivity() {

    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    //null olabilir.
    private val currentUser: FirebaseUser? by lazy { firebaseAuth.currentUser }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        if (firebaseAuth.currentUser != null) {

            val intent=Intent(this,GuestActivity::class.java)
            startActivity(intent)
            finish()
        }

        initEvent()
    }


    private fun initEvent() {

        Glide.with(this).load(R.drawable.splash_screen_bg).into(imageView)

        splash_screen_sign_in.setOnClickListener {

            splash_screen_sign_in.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))

            val intent= Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)

        }

        splash_screen_sign_up.setOnClickListener {

            splash_screen_sign_up.setTextColor(ContextCompat.getColor(applicationContext, R.color.colorAccent))

            val intent=Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)


        }
    }
}
