package com.fburaky.kotlinalertdialog

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(applicationContext,"Welcome",Toast.LENGTH_LONG).show()

    }

    fun save(view : View){

        val alert = AlertDialog.Builder(this@MainActivity)
        alert.setTitle("Save")
        alert.setMessage("Are you sure?")
        alert.setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(applicationContext,"Saved",Toast.LENGTH_LONG).show()
        })

        alert.setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(applicationContext,"Not Save",Toast.LENGTH_LONG).show()
        })

        alert.show()

    }

}