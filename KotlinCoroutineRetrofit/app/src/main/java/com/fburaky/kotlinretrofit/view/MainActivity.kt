package com.fburaky.kotlinretrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fburaky.kotlinretrofit.R
import com.fburaky.kotlinretrofit.adapter.RecyclerViewAdapter
import com.fburaky.kotlinretrofit.model.CryptoModel
import com.fburaky.kotlinretrofit.service.CryptoAPI
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener{

    private val BASE_URL= "https://api.nomics.com/v1/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null
    private var job : Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Eror : ${throwable.localizedMessage}")
    }

    // Disposable kullan at , Tek kullanımlık demektir.
    // Bizim yaptığımız bu call'ları yani bu istekleri kullan at gibi değerlendirip
    // Aktivitemiz veya fragmant silindiğinde yani yaşam döngüsünden çıktığında
    // bu Call'lardan kurtulmamız gerekmekteyiz ki hafızada yer tutmasın .
    // RxJavanın compositeDisposable objesi mevcut farklı farklı tek kullanımları birleştirip tek bir çöp haline getiren
    // Ve aktivite devam ederken direk kurtulabileceğimiz bir objedir .

    private var compositeDisposable : CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //6a462911100e4a2901f40df44e67dff9
        //https://api.nomics.com/v1
        //https://api.nomics.com/v1/prices?key=6a462911100e4a2901f40df44e67dff9

        compositeDisposable = CompositeDisposable()

        //Reycler View
        val layoutManager :RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        loadData()
        //Retrofit objesini oluşturup internetten çekelim
        
    }

    // Aşağıdaki metotta ise RxJava kütüphanesini kullanabilmem için gerekli işlemleri yapıyorum
    private fun loadData(){

        // Aşağıdaki kod ile retrofit objesini oluşturmuş oluyoruz .
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                // Aşağıdaki kodumuzda Retrofite , RxJava kullanacağımızı gösteriyoruz .
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {

            val response = retrofit.getData()
            withContext(Dispatchers.Main){

                if (response.isSuccessful){
                    response.body().let {
                        // Response boş değilse
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@MainActivity)
                            recyclerView.adapter=recyclerViewAdapter
                        }
                    }
                }
            }

        }
    }

    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this , "Clicked : ${cryptoModel.currency}" , Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }
}