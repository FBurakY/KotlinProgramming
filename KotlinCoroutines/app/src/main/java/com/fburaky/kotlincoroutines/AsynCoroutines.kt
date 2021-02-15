package com.fburaky.kotlincoroutines

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    var userName = ""
    var userAge = 0

    runBlocking {

        /*
        launch {
            val downloadedName = downloadName()
            userName = downloadedName
        }

        launch {

            val downloadedAge = downloadAge()
            userAge = downloadedAge
        }

        launch {
            println("${ userAge} ${userName}")
        }
        // Not : Çıktı olarak sadece bir değişkenin sonucunu verecektir.
        // Oysaki biz iki sonucu görmek istiyoruz .
        // Bu iki sonucu görmemiz için asekron çalışmaları gerekmektedir.
         */

        val downloadedName = async {
            downloadName()
        }

        val downloadedAge = async {
            downloadAge()
        }

        userName = downloadedName.await()
        userAge = downloadedAge.await()
        // Yukarıdaki kodda , bu atama işlemlerinin bitmesini bekliyecektir.
        println("${ userAge} ${userName}")
    }
}

suspend fun downloadName() : String {
    delay(2000)
    val userName = "Fadil Burak :"
    println("username download")
    return userName
}

suspend fun downloadAge() : Int{

    delay(4000)
    val userAge = 60
    println("userage download")
    return userAge
}