package com.fburaky.kotlinintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_next.*

class NextActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)

        //GetIntent
        val intent = intent
        val username = intent.getStringExtra("username")
        val name     = intent.getStringExtra("name")

        usernameTextNextActivity.text = "Username:" + username
        nameTextNextActivity.text = "Name :" + name

    }

}