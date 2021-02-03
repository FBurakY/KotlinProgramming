package com.fburaky.kotlinoopproject

open class Musician(name: String, instrument: String, age: Int) {

    /*
    // Public olarak yazmak istersem aşağıdaki gibi kodluyorum
    var name : String? = name
    var instrument : String? = instrument
    var age : Int? = age

     */

    // Encapsulation
    var name : String? = name
        private set
        get


    private var instrument : String? = instrument

    var age : Int? = age
        get
        private set

    // Aslında , istediğimiz sınıfta tamamen private ve val olarak oluşturduğumuz constlarıda
    // Diğer taraflara aktaracağımız metotlarıda manuel olarak yazabilirim .
    // Ve böylece o değerleri ve metotları istediğimiz yerde kullanabiliriz .
    private val bandName : String = "Metallica"
    fun returnBandName(password : String) : String{

        if (password == "Fadil Burak"){
            return  bandName
        }
        else{
            return "Wrong Password"
        }

    }
}