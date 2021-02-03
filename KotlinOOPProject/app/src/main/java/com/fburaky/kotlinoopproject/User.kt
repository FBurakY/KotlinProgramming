package com.fburaky.kotlinoopproject

class User : People {

    var name : String? = null
    var age : Int? = null

    // Constructor 'Inşa Etmek'
    // Constructor , sınıftan bir obje oluşturulduğunda çağırılan ilk metotdur.
    constructor(nameInput:String , ageInput:Int){
        this.name = nameInput
        this.age = ageInput
        // Yukarıda ki kodda , her User objesi oluşturmaya çalıştığımızda
        // nameInput:String , ageInput:Int değişkenlerini alıyor ve bunu property'e atıyor .

    }

    init {
        // init , kotlinde sınıftan bir obje çağırdığımız zaman ilk çağırılan bir metottur yalnız .
        // Constructor yukarıdaki gibi kullanmayız .
        println("init")
    }




}