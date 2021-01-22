package com.fburaky.kotlincatchthekenny

import android.content.DialogInterface
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var score = 0
    var imageArray = ArrayList<ImageView>()
    var handler = Handler(Looper.getMainLooper())
    var runnable = Runnable {  }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //imageArray
        imageArray.add(imageView)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
        hideImages();

        // Geri sayım için .
        object : CountDownTimer(15500,1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Time "+millisUntilFinished/1000

            }

            override fun onFinish() {

                handler.removeCallbacks(runnable)

                for (image in imageArray){
                    image.visibility = View.INVISIBLE
                }

                timeText.text = "Time 0"

                // Alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setTitle("Restart the Game")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    //Restart

                    // Aşağıdaki kod aktivitemin . Yollandığı intenti göstermektedir.
                    // Put Ekstra ile ilgili bir veri yolladıysak onu almamızı sağlıyor .
                    val intent = intent
                    //Aşağıda yazdığım kod ise bu aktivitinin kendisini tekrar başltan başlatmamızı sağlamaktadır.
                    finish()
                    startActivity(intent)

                })
                alert.setNegativeButton("Mo",DialogInterface.OnClickListener { dialog, which ->
                    //Game Over,
                    Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_LONG).show()
                })
                alert.show()
            }
        }.start()
    }

    fun increaseScore(view: View){

        score = score + 1
        scoreText.text = "Score: $score"
    }

    fun hideImages(){

        runnable = object : Runnable {
            override fun run() {

                for (image in imageArray) {

                    // Bütün resimleri görünmez yaptım
                    image.visibility = View.INVISIBLE

                }

                val random =  Random()
                val randomIndex = random.nextInt(9)
                imageArray[randomIndex].visibility = View.VISIBLE

                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
}