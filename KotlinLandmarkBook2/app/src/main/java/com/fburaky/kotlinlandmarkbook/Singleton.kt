package com.fburaky.kotlinlandmarkbook

import android.graphics.Bitmap

class Singleton {

    /* Singleton Nedir ?
       +Tek bir objesi olan
       +Tek bir instance değişkeni olan sınıftır.
       +Biz bunu Java'da Constraction private olmakta , sadece bu sınıf içersiinde obje oluşturabiliriz .
                             sonra diğer sınıflardan o objeye ulaşılabilir şekilde ayarlamasını yaparız .
    */

    /* + Aşağıdaki kodu yazdığımızda , bu obje türünü herhangi bir sınıfta oluşturduğumuzda    .
            artık tüm sınıflardan , aynı değişkene veya aynı sınıfın özelliğine ulaşabiliyoruz  .
    */
    companion object Selected{

        var selectedImage : Bitmap?=null

    }

}