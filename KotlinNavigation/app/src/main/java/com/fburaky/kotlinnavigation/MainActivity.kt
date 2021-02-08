package com.fburaky.kotlinnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    /*
        + Navigasyon Nedir ?
        Fragment'ları kullanırken navigation dediğimiz kütüphane zaruri bir durumua gelmiş halde.
        Navigasyon dediğimizde harita navigasyonu değil uygulma içerisinde ki nereden nereye gidilecek
        ve bunlar arasında nasıl bağlantılar var . Bunu görsel olarak gösteren yapıdır . Ve özellikle
        fragmentlarla çalışırken veri aktarımı biraz zorluk yaratmaktadır . Bu sebeble navigasyon çok önemlidir.
     */
}