package com.fburaky.kotlinretrofit.service

import com.fburaky.kotlinretrofit.model.CryptoModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {
    // GET POST UPDATE DELETE
    // VERİ ÇEKMEK İÇİN GET
    // VERİYİ YAZMAK VE DEĞİŞTİRMEK İÇİN POST
    // GÜNCÜELLEMEK İÇİN UPDATE
    // SİLMEK İÇİN İSE DELETE METODUNU KULLANIYORUZ.

    @GET("prices?key=6a462911100e4a2901f40df44e67dff9")
    // Observable Gözlemlenebilir bir obje demektir .
    // Gözlemlenebilir obje demek , veriler geldiğinde alan ve bu verileri yayın yapan obje demektir .
    // Bir değişiklik olduğunda , bu objeyenin yayınına abone olana bilgi yapan objedir.
    fun getData():Observable<List<CryptoModel>>

    // Bu Call Retrofit için geçerli olan bir metot
    //fun getData():Call<List<CryptoModel>>
}