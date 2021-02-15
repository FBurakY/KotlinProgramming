package com.fburaky.kotlincoroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    /*  ************** JOB *****************
        launch dediğimizde karşımıza job çıkabiliyor .
        bize döndürülen bu işlemleri biz sonradan kullanabiliyoruz . İster iptal ederiz , ister bitiminde şunları yaparız
        veya isterde bitiminde farklı işlemler yapabiliriz.
     */

    runBlocking {

        val myJob = launch {
            delay(2000)
            println("Job")

            val secondJob = launch {
                println("Job 2")
            }
        }
        myJob.invokeOnCompletion {
            println("My Job End")
            // Benim işim tamamlandığında neler yapabileceğimi yazabiliyorum.
        }

        myJob.cancel()
    }
}