package com.fburaky.kotlinsqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{

            val myDatabase = this.openOrCreateDatabase("Musicians" , MODE_PRIVATE , null)

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY , name VARCHAR , age INT)")

            //myDatabase.execSQL("INSERT INTO musicians( name , age ) VALUES('Kırk',55)")
            //myDatabase.execSQL("INSERT INTO musicians( name , age ) VALUES('Inna',65)")
            //myDatabase.execSQL("UPDATE musicians SET age = 61 WHERE name ='Inna'")
            //myDatabase.execSQL("DELETE FROM musicians WHERE name='Inna'")


            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians" , null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name = 'Inna'" , null)
            // Sonu s ile bitenleri %s veya K ile başlayan dersem K% şeklinde yazıyoruz.
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'" , null)

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians " , null)
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")



            while(cursor.moveToNext()){
                println("Name :" +cursor.getString(nameIx))
                println("Age  :" +cursor.getString(ageIx))
                println("Id  :" +cursor.getString(idIx))
            }
            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }

    }
}