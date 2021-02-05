package com.fburaky.kotlintravelbook.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.fburaky.kotlintravelbook.Adapter.CustomAdapter
import com.fburaky.kotlintravelbook.MapsActivity
import com.fburaky.kotlintravelbook.Model.Place
import com.fburaky.kotlintravelbook.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    var placesArray = ArrayList<Place>()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_place,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.add_place_option){
            val intent = Intent(applicationContext , MapsActivity::class.java)
            intent.putExtra("info" , "new")
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {

            val database = openOrCreateDatabase("Places", Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT *FROM places",null)
            val addressIndex = cursor.getColumnIndex("address")
            val latitudeIndex = cursor.getColumnIndex("latitude")
            val longitudeIndex = cursor.getColumnIndex("longitude")

            while (cursor.moveToNext()){

                val addressFromDatabe = cursor.getString(addressIndex)
                val latitudeFromDatabese = cursor.getDouble(latitudeIndex)
                val longitudeFromDatabase = cursor.getDouble(longitudeIndex)

                val myPlace = Place(addressFromDatabe,latitudeFromDatabese,longitudeFromDatabase)
                placesArray.add(myPlace)

                println(myPlace.address)

            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }


        val customAdapter = CustomAdapter(placesArray , this)
        listView.adapter = customAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("info","old")
            intent.putExtra("selectedPlace",placesArray.get(position))
            startActivity(intent)
        }
    }
}