package com.fburaky.kotlincoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* + Light Weightness
           + Coroutine , threadlere nazaran çok yalın ve hafif iş parçacıklarıdır.

        GlobalScope.launch {
            // 100.000 Adet Coroutines oluşturuyoruz.
            repeat(100_000){
                launch {
                    // Ve bu Coroutinesler Android yazmakta.
                    // Uzun sürebilecek işlemler için coroutinesleri kullanmaktayız.
                    println("Android")
                }
            }
        }*/

        // **************** SCOPE 'KAPSAM' ********************
        //      -> Global Scope   : Bütün uygulama içerisinde çalıştırabileceğimiz bir kapsamda açıyoruz coroutine yapısıdır.
        //      -> runBlocking    : Scopeları blocklayarak oluşturuyor . Yani öncesindeki işlemleri yapıyoruz .
        //                           runBlocking geliyor içerisindeki coroutine işlemleri bitene kadar scope dışındaki kodların çalışmasını durduruyor .
        //                            önceliği runBlocking içerisindeki kodlar oluyor . runBlocking içerisindeki scope işlemleri bitmeden alt scope geçmiyor.
        //      -> CoroutineScope : Scope'nun içerisindeki bütün coroutinelerin işlemlerinin bitmesini bekler .
        //*********** runBlocking Scope **************
        println("Run Blocking Start")
        runBlocking {
            // Coroutinelerimizi başlatma kodumuz launch .
            launch {
                delay(2000)
                println("Run Blocking")
            }
        }
        println("Run Blocking End")
        //*********** Global Scope **************
        println("Global Blocking Start")
        GlobalScope.launch {
            delay(5000)
            println("Global Blocking")
        }
        println("Global Blocking End")

        // *********** Coroutine Scope **************
        // Coroutine Context -> Scope Coroutineleri oluşturuyor ve yönetiyor . Contex ise , hangi veriler ile birlikte çalışacağını aslında söylüyor.
        //                       veriler ile birlikte çalıyor derken . Farklı threadlerde çalışmak veya farklı işlemlere farklı seneryolar geliştirmektir.
        //                        aşağıdaki kodumuzda Dispatchers. yazdıktan bizim coroutinemizin hangi thread içerisinde çalışağıyla ilgilenmekte.
        //                         coroutine scope'da hangi thread içerisinde çalıştıracağımızı belirlememizdir. Global Scope ise bütün uygulamadaki thread havuzunda çalışmakta.
        println("Coroutine scope Start")
        CoroutineScope(Dispatchers.Default).launch {
            delay(5000)
            println("Coroutine Scope")
        }
        println("Coroutine scope End")
    }
}