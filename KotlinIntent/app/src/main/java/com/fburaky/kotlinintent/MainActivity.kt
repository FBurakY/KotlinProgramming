package com.fburaky.kotlinintent


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        println("on Resume Called")
    }

    override fun onPause() {
        super.onPause()
        println("on Pause Called")
    }

    override fun onStop() {
        super.onStop()
        println("on Stop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("on Destroy Called")
    }

    fun next(view: View){

        val intent = Intent(applicationContext , NextActivity::class.java)

        intent.putExtra("username",usernameText.text.toString())
        intent.putExtra("name",nameText.text.toString())
        startActivity(intent)
        finish()
    }
}