package com.fburaky.kotlinlandmarkbook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

// Global değişkenler pek kullanılmaz !
// Aşağıdaki global değişken güvenli değildir
//var selectedGlobalBitmap: Bitmap ?= null

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Data
        var landmarkName = ArrayList<String>()
        landmarkName.add("Pisa")
        landmarkName.add("colosseum")
        landmarkName.add("Eiffel")
        landmarkName.add("London Bridge")

        /*
        //Image
        val pisa = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.pisa)
        val colosseum = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.colosseum)
        val eiffel = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.eiffel)
        val londonBridge = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.londonbridge)

        var landmarkImages = ArrayList<Bitmap>()
        landmarkImages.add(pisa)
        landmarkImages.add(colosseum)
        landmarkImages.add(eiffel)
        landmarkImages.add(londonBridge)
        */

        // Image - Efficient
        // Tek Tek tanımlamak istemiyorum id'ye göre tanımlamak istiyorum.
        val pisaId = R.drawable.pisa
        val collosseumId = R.drawable.colosseum
        val eifelId = R.drawable.eiffel
        val londonBridgeId = R.drawable.londonbridge

        var landmarkImageIds = ArrayList<Int>()
        landmarkImageIds.add(pisaId)
        landmarkImageIds.add(collosseumId)
        landmarkImageIds.add(eifelId)
        landmarkImageIds.add(londonBridgeId)



        //Adapter : Layout & Data
        // Default Layout
        // val adapter = ArrayAdapter(this , android.R.layout.simple_list_item_1 , landmarkName)
        // Kendimizin oluşturduğu Layout
        val adapter = ArrayAdapter(this , R.layout.list_row ,R.id.textView2, landmarkName)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val intent = Intent(applicationContext , DetailsActivity::class.java)

            intent.putExtra("name",landmarkName[position])

            intent.putExtra("image", landmarkImageIds[position])


            // Global Değişken -> selectedGlobalBitmap = landmarkImages[position]
            /*
            val singleton = Singleton.Selected
            singleton.selectedImage = landmarkImages[position]
             */


            startActivity(intent)
        }
    }
}