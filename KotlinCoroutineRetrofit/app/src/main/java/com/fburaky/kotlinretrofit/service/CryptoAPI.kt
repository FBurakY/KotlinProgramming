package com.fburaky.kotlinretrofit.service

import com.fburaky.kotlinretrofit.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("prices?key=6a462911100e4a2901f40df44e67dff9")
    suspend fun getData(): Response<List<CryptoModel>>
}