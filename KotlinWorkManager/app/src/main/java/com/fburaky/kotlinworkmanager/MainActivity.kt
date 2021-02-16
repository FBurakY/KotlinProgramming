package com.fburaky.kotlinworkmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RefreshDatabase ' aşağıda ki şekilde veriyi gönderiyoruz.
        val data = Data.Builder().putInt("intKey",1).build()

        //Aşağıdaki kodumuzda arkada çalışmasını istediğimiz olayları en ince detayına göre kodlayabiliyorum.
        // Constraints ile çalışırken , kendi koşullarımızı yazdığımız bir yapıdır .
        val constraints = Constraints.Builder()
            //.setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresCharging(false)
            .build()


        /*
        // Şimdi Work-Managerın çalışabilmesi için gerekli olan kodlamayı yazalım
        // Bir seferlik çalışan work'u kodlayalım.
        val myWorkRequest : WorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            //.setInitialDelay(5,TimeUnit.MINUTES)
            //.addTag("myTag") // addTag bu metodu birden fazla work yaparsak eğer kullanıyoruz , Id atıyor fakat tag atıyarakda kullanabiliriz.
            .build()

        //Work-Managerı çalıştırabilmek için;
        WorkManager.getInstance(this).enqueue(myWorkRequest)
         */
        // Work-Manager'ın periyodik olarak çalışmasını istediğimiz işlemleri aşağıdaki kodumuzda yapıyoruz.
        val myWorkRequest : PeriodicWorkRequest = PeriodicWorkRequestBuilder<RefreshDatabase>(15,TimeUnit.MINUTES)
            .setConstraints(constraints)
            .setInputData(data)
            .build()
        WorkManager.getInstance(this).enqueue(myWorkRequest)

        //Work-Manager Servisimizin çalışıp çalışmadığını hata verip vermediğini gözlemek için ;
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(myWorkRequest.id).observe(this , Observer {
            if (it.state == WorkInfo.State.RUNNING){
                println("Work-Manager Running")
            }
            else if (it.state == WorkInfo.State.FAILED){
                println("Work-Manager FAILED")
            }
            else if (it.state == WorkInfo.State.SUCCEEDED){
                println("Work-Manager SUCCEEDED")
            }
        })

        //Work-Manager iptal etmek istersek eğer ;
        //WorkManager.getInstance(this).cancelAllWork()

        // Arka arkaya worklerimizi zincirlemek istersek.
        // Yani demek istediğim work-manager ilk başta şunu ardından farklı şu işlemi yap vs. şeklinde zincirleme çalışmasını istiyorsak. Chaning
        // NOT: Burada dikkat edilmesi gerekn nokta periyodik work-managerlarda bu işlem çalışmaz.
        //      Tek seferlik başlangıçta tanımladığımız OneTimeWorkRequestBuilder'de çalışmaktadır.
        val oneTimeWorkRequest : OneTimeWorkRequest = OneTimeWorkRequestBuilder<RefreshDatabase>()
            .setConstraints(constraints)
            .setInputData(data)
            .build()

        WorkManager.getInstance(this).beginWith(oneTimeWorkRequest) // ilk önce oneTimeWorkRequest ile başlamasını ardından
            .then(oneTimeWorkRequest)   // then içindeki workler ile devam etmesini
            .then(oneTimeWorkRequest)   // then içindeki workler ile devam etmesini
            .enqueue()
        //Yukarıda şekilde yaptığımız kodlamada work lerimizi birbirine zincirlemiş oluyoruz.
    }
}