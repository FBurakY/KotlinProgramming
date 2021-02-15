package com.fburaky.kotlincoroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    // Dispatchers :
    //              -> Dispatchers.Default : Bu CPU Intensive , CPU'nun yoğun çalışması gereken durumlarda tercih ediliyor .
    //                  örnek vermek gerekir ise görsel işleme , çok uzun bir diziyi alfabetik olarak sıralayıp dizmek .
    //              -> Dispatchers.IO : Input / Output ; Network işlemleri yani veri tabanından verileri çekmek . Internetten bir şeyler yapacağımız zaman bunu tercih ederiz.
    //              -> Dispatchers.Main : UI , kullanıcının arayüzünde görmesini istediğimiz işlemlerde bunu tercih ediyoruz .
    //              -> Dispatchers.Unconfig : İçerisinde çalıştırılan yere göre miras alıyor .

    runBlocking {

        // Main Threadi Main Activity içerisinde çalışmalıdır .
        launch(Dispatchers.Main) {
                  println("Main Thread : ${Thread.currentThread().name}")
        }
        launch(Dispatchers.IO) {
            println("IO Thread : ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Default) {
            println("Default Thread : ${Thread.currentThread().name}")
        }

        launch(Dispatchers.Unconfined) {
            println("Unconfined Thread : ${Thread.currentThread().name}")
        }

    }
}