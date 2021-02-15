package com.fburaky.kotlincoroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){

    // ******** Coroutine içerisinde Coroutine yapmak için :
    runBlocking {
        launch {
            delay(5000)
            println("Run Blocking")
        }

        coroutineScope {
            launch {
                delay(3000)
                println("coroutine Scope")
            }
        }
    }
}