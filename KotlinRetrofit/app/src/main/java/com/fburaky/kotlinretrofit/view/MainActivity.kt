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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener{

    private val BASE_URL= "https://api.nomics.com/v1/"
    private var cryptoModels : ArrayList<CryptoModel>? = null
    private var recyclerViewAdapter : RecyclerViewAdapter? = null

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

        /*
        Ardından compositeDisposable isminde bir obje kullanıyoruz . Bu obje farklı Callları add diyerek ekliyoruz .
        Gelen veriyi arka planda dinliyor bizim Main Threadimizde işliyor . Ve handleResponse tarafına aktarıyor .
         */
        compositeDisposable?.add(retrofit.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse))
    }

    private fun handleResponse(cryptoList : List<CryptoModel>){

        cryptoModels = ArrayList(cryptoList)

        cryptoModels.let {
            recyclerViewAdapter = RecyclerViewAdapter(it!!,this@MainActivity)
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    /*
    // Aşağıdaki metotta kullandığımız Call'da Retrofit için kullanıyoruz.
    private fun loadData(){
        
        // Aşağıdaki kod ile retrofit objesini oluşturmuş oluyoruz .
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        
        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()
        
        call.enqueue(object : Callback<List<CryptoModel>> {

            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
                if (response.isSuccessful){
                    response.body()?.let {

                        cryptoModels = ArrayList(it)

                        cryptoModels.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it!!,this@MainActivity)
                            recyclerView.adapter = recyclerViewAdapter
                        }
                        /*
                        for(cryptoModel: CryptoModel in cryptoModels !!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }
                        */
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
                // Loglarda ne hata olduğunu bize gösterecek
            }
        })
    }
    */
    override fun onItemClick(cryptoModel: CryptoModel) {
        Toast.makeText(this , "Clicked : ${cryptoModel.currency}" , Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        //İşte RXJava nın anlamını aşağıdaki kodda anlıyoruz . Belleği temizliyoruz
        //Bu şekilde hafızanın şişmesini engelliyoruz .
        compositeDisposable?.clear()
    }
}