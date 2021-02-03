package com.fburaky.kotlinoopproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Aşağıdaki kodda User sınıfından bir myUser objesini oluşturdum.
        var myUser = User(nameInput = "Fadil Burak" , 25)

        /*
        myUser.age = 26
        myUser.name = "Fadil Burak"
        */

        println(myUser.age.toString())
        println(myUser.name.toString())
        // Kalıtım ile information metoduna eriştim.
        println(myUser.information())

        // --------------------------Encapsulation-----------------
        var james = Musician("James","Guitar",55)
        //james.age=60
        //println(james.age.toString())
        // Age altına Set & Get Yaz dığım için erişe biliyorum .
        println(james.age.toString())

        james.returnBandName("Yurtsever")
        james.returnBandName("Fadil Burak")

        // Inheritance , bir sınıf oluşturduğumuzda aşka bir sınıf bunun kalıtımını alabilir
        // başka bir sınıfta bunun tüm metotları , değişkenlerini vs. kullanabilir .

        // Inheritance
        var lars = SuperMusician("Burak","Drums",30)
        println(lars.name)
        println(lars.returnBandName("Fadil Burak"))
        lars.sing()

        // Polymorphism , aynı ismi kullanarak farklı işlemler yapabilme yeteneğidir.
        // Static Polymorphsim
        var mathematics = Mathematics()
        println(mathematics.sum())
        println(mathematics.sum(6,9))
        println(mathematics.sum(2,3,4))

        // Dynamic Polymorphsim , farklı sınıflarla bu işlemi yapıyorsak eğer .
        val animal = Animal()
        animal.sing()

        val lykos = Dog()
        lykos.test()
        lykos.sing()

        // ----------------------Abstract & Interface-----------------
        // Abstract , soyut sınıf aslında bizim bir obje/instance oluşturamaaycağımız bir sınıftır .
        // Abstract kalıtıma maruz kalınmasını istediğimiz sınıflarda kullanırlır .
        // -> var myPeople = People() , burada hata alırız çünkü Soyut Sınıfların objesi oluşturulamaz.
        // Kalıtımı sadece tek bir sınıf için yapabilmekteyiz !
        // Yani iki sınıftan aynı anda inheritance/Kalıtım yapamıyoruz .


        // Ara yüzleri'Interface' kullanarak , %100 Abstract yapılar oluşturabilir ve birden fazla implemets edebiliyoruz.

        var myPiano = Piano()
        myPiano.brand = "Yamaha"
        myPiano.digital= false
        println(myPiano.roomName)
        myPiano.info()

        //Lambda Expressions , bir fonksiyonu tek bir satırda yazmak için kullandığımız bir gösterimdir .

        fun printString(myString : String){
            println(myString)
        }
        printString("My Test String")

        val testString = {myString : String -> println(myString)}

        testString("My Lambda String")

        val multiplyLambda ={a:Int , b:Int -> a * b}
        println(multiplyLambda(5,9))

        val multiplyLambda2 : (Int , Int) -> Int = {a,b -> a * b}
        println(multiplyLambda2(14,55))

        // ----------------------------                Asynchrnous                     ----------------------------------
        // Sekronize olmayan bir işlem yaptığımızda , genelde bir işlem uzun sürerken onu beklemeyip farklı işlemleride yapmamıza olanak sağlıyor.
        // Örneğin internetten bir veri indireceğiz . İnternetten veri çekmek bir işelem ve bu sırada UI ' de kullanıcı  farklı işlemler yapıyor.
        // Eğer biz internetten bir işlem yaparken veya büyük bir işlem yaparken . Asekron bir işlem yapmazsak UI ' yi kitleriz.
        // Bu gibi işlemler için asekron çalışır ve tamamlandığında ne yapılacağını yazdığımız bazı metotlar vardır .
        // callback Funcition , listener function , completion function gibi metotlar mevcuttur .
        // işte bu kısımlarda lambda gösterimi çok sık kullanılır .

        fun downloadMusicians(url:String , completion: (Musician) -> Unit){
            // Url - > download
            // Data
            val kirkHammet = Musician("Kirk","Zurna",60)
            completion(kirkHammet)
        }

        downloadMusicians("www.bilgi.com",{
            println(it.name)
        })

    }
}