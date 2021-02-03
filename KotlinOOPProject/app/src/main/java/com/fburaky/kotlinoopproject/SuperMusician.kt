package com.fburaky.kotlinoopproject

class SuperMusician(name: String, instrument: String, age: Int) : Musician(name, instrument, age) {

    // SuperMusician sınıfı Musician sınıfının bütün özelliklerine sahip
    // Ve kendisine ait üst özellikleride mevcut olabiliecek.

    fun  sing(){
        println("Nothing Else Matters")
    }

    /*
        NOT : Kotlinde sınıflar defauolt olarak final olarak tanımlanır .
              yani başka bir yerlerde kullanamaz ve kalıtım yapamazsın . Kalıtım yapabilmek için
              sınıfımızın başına open ekliyoruz .
     */
}