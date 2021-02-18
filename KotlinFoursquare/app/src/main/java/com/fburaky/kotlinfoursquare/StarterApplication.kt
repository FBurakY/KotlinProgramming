package com.fburaky.kotlinfoursquare

import android.app.Application
import com.parse.Parse

class StarterApplication : Application() {

    // Parse sunucu ile bağlantımızı oluşturabilmek için bu sınıfı kullanacağız.

    override fun onCreate() {
        super.onCreate()
        //Log-Catte işlemleri görmek için aşağıdaki kodu yazıyorum ;
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG)

        // Aşağıdaki kodumuzda , parse-sunucu ile iletişim halinde oluyoruz .
        Parse.initialize(Parse.Configuration.Builder(this)
            .applicationId("7MLaG0H6yxTveTWUFZ122hKwcsB9BLEXCYHlJLVG")
            .clientKey("DMN8QMMPwjbHqYwhB8LmqWPf5vXrWYGSQvAEuQXP")
            .server("https://parseapi.back4app.com/")
            .build())
    }
}