package com.fburaky.kotlinoopproject

class Dog: Animal() {

    fun test(){
        // Super yazdığımız da kalıtım aldığımız sınıfa referans verir . Yani Animal()
        super.sing()
    }

    override fun sing(){
        println("Dog Class")
    }

}