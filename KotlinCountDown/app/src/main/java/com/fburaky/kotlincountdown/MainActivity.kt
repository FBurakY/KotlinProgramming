package com.fburaky.kotlincountdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Abstract - Inheritance :
        object : CountDownTimer(10000,1000) {
            override fun onTick(millisUntilFinished: Long) {
                // Belirtilen her aşamada ne olacağını buraya yazıyoruz.
                textView.text = "Left: ${millisUntilFinished/1000}"
            }
            override fun onFinish() {

                // İşlem bitince ne olacağı buraya yazıyoruz.
                textView.text ="Left: 0"

            }
        }.start()
    }
}