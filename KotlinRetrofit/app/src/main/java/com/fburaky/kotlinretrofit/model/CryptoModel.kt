package com.fburaky.kotlinretrofit.model

import com.google.gson.annotations.SerializedName

data class CryptoModel(
        //@SerializedName("currency")
        // Kotlinde Serileştirmeye gerek yok sebebi ise yapıcı metottaki parametre adını
        // Json'dan gelen veri ile aynı yapıyor olmam.
        val currency:String ,
        //@SerializedName("price")
        val price:String)
    /*
      + Data classın amacı :
        İçerisine tamamen veri çekebileceğimiz.
        İçerisine çektiğimiz verileri işleyeceğimiz bir yapı oluşturmak .
        ve bu data classın constructorında çekeceğimiz verilerin parametreleri olur.
        bu verileri işleyebilmek için gsondan faydalanacağımız için :
        @SerializedName("currency") kodunu yazıyoruz bu kodda
        parametresi currency olacak . ve bunu alıp yapıcı metottaki parametreye
        ataması gerektiğini söyliyorum
     */