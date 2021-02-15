package com.fburaky.kotlincoroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(){

    /*
       + Suspend Foksiyonlar : Içerisinde coroutine çalıştırılabilen fonksiyonlardır .
         -> Bu fonksiyonlar , istediğimiz zaman durdurabilir veya devam edebiliriz.
         -> Ve bunun çalışmasını android işletim sistemimiz ayarlıyor .
         -> Eğer ki içerisinde içerisinde bir coroutine çalışmasını istiyorsak o metodun suspend olması gerekmektedir.
     */

    runBlocking {
        delay(2000)
        println("run blocking")
        myFunction()
    }
}

suspend fun myFunction(){

    coroutineScope {
        delay(4000)
        println("Suspend function")
    }
}