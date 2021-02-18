package com.fburaky.kotlinfoursquare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.parse.LogInCallback
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        // Parse Veri eklemek için aşağıdaki kodu yazıyoruz.
        val parseObj = ParseObject("Fruits")
        parseObj.put("name","orange")
        parseObj.put("calories",200)
        parseObj.saveInBackground { e ->
            if (e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(applicationContext,"Saved".toString(),Toast.LENGTH_LONG).show()
            }
        }
         */


        //Parse Sunucusunda ki oluşturduğum Meyveler tablosunun içindeki verileri çekelim :
        /*
        val query = ParseQuery.getQuery<ParseObject>("Fruits")
        query.whereEqualTo("name","orange")
        //query.whereLessThan("calories",100)
        query.findInBackground { objects, e ->
            if (e != null){
                Toast.makeText(applicationContext,e.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if (objects.size > 0){
                    for (parseObject in objects){
                        val name = parseObject.get("name") as String
                        val calories = parseObject.getInt("calories") as Int
                        println("Name :" + name)
                        println("Calories :"+ calories)
                    }
                }
            }
        }*/
    }

    fun signIn(view: View){

        ParseUser.logInInBackground(usernameText.text.toString() , passwordText.text.toString() ,
            LogInCallback { user, e ->

              if (e != null){
                // Eğer bir hatamız var ise :
                  Toast.makeText(applicationContext , e.localizedMessage , Toast.LENGTH_LONG).show()
              }
                else{
                  Toast.makeText(applicationContext , "Welcome " +user.username.toString() , Toast.LENGTH_LONG).show()
                  val intent = Intent(applicationContext ,LocationsActivity::class.java)
                  startActivity(intent)
              }
            })

    }

    fun signUp(view: View){

        val user = ParseUser()
        user.username = usernameText.text.toString()
        user.setPassword(passwordText.text.toString())
        user.signUpInBackground {
            if (it != null){
                Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                //Hata yok ise :
                Toast.makeText(applicationContext,"User Created",Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext,LocationsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}