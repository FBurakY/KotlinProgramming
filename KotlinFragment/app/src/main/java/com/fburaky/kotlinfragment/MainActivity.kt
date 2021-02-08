package com.fburaky.kotlinfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Fragment Nedir ?
        Aktivitelerden daha verimli çalışabilmek için kullandığımız bir yapıdır.
        Aktiviteler içerisinde yaptığımız tüm işlemleri yapabilmekteyiz.
        Fragmentin kendine ait bir yaşam döngüsü mevcuttur.
        Birden fazla fragmana tek bir aktiviteye bağlaya biliriz.
         */

    }


    fun firstFragment(view : View){

        /*
        Fragmanları kullanabilmemiz için , fragmanları yöneten bir yapıyı kullanmamız gerekmekte .
        Bunun için ise ; FragmentManager'i kullanacağız
         */
        val fragmentManager = supportFragmentManager
        // Fragmenti başlatmak için ise ;
        val fragmentTransaction = fragmentManager.beginTransaction()

        val firstFragment = BlankFragment()
        fragmentTransaction.replace(R.id.frameLayout , firstFragment).commit()
    }

    fun secondFragment(view: View){

        /*
       Fragmanları kullanabilmemiz için , fragmanları yöneten bir yapıyı kullanmamız gerekmekte .
       Bunun için ise ; FragmentManager'i kullanacağız
        */
        val fragmentManager = supportFragmentManager
        // Fragmenti başlatmak için ise ;
        val fragmentTransaction = fragmentManager.beginTransaction()

        val secondFragment = BlankFragment2()
        fragmentTransaction.replace(R.id.frameLayout , secondFragment).commit()

    }

}