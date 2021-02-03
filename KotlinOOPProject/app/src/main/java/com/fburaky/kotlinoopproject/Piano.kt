package com.fburaky.kotlinoopproject

class Piano : HouseDecor , Instrument{

    var brand: String? = null
    var digital:Boolean? = null

    // Değişkeni tanımladığımız yer
    override var roomName: String
        get() = "Kitchen"
        set(value) {}

    /*
    override fun info() {
        TODO("Not yet implemented")
    }
     */
}