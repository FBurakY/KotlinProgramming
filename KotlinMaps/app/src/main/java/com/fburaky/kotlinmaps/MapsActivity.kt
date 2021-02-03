package com.fburaky.kotlinmaps

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var locationManager:LocationManager
    private lateinit var locationListener: LocationListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapLongClickListener(myListener)
        /*
        // Add a marker in Sydney and move the camera
        // Latitude & Longitude -> LatLng 'Enlem - Boylam'
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
         */

        // Bizim iki tane objeye ihtiyacımız var konum işlemleri yapabilmemiz için
        // Konum yöneticisi -> Konum ile ilgili durumlar yönetir.
        // LocationManager bütün süreci yönetmemizi sağlayan bir objedir
        // Konum Dinleyicisi -> Konumu dinler değişiklik olduğunda bize haber veriyor

        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location) {
                // Kullanıcının konumu değiştiğinde ne yapacağımızı buraya yazıyoruz .
                if (location != null){

                    mMap.clear()

                    val userLocation = LatLng(location.latitude , location.longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("Your Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation ,15f))

                    val geocoder = Geocoder(this@MapsActivity , Locale.getDefault())

                    try {
                        val addressList = geocoder.getFromLocation(location.latitude,location.longitude,1)
                        if (addressList != null && addressList.size >0 ){
                            println(addressList.get(0).toString())
                        }
                    }catch (e:Exception){
                        e.printStackTrace()
                    }



                }
            }
        }

        // Kullanıcıdan konum izni almak için aşağıdaki kodumuzu yazalım
        if (ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            // Kullanıcıdan ilk izini almamız için aşağıdaki kodu yazıyoruz.
            ActivityCompat.requestPermissions(this , arrayOf(Manifest.permission.ACCESS_FINE_LOCATION) , 1)

        }
        else{
            // Kullanıcının izini varsa aşağıdaki kodlar çalışacak
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER , 1 , 1f , locationListener)
            // Kullanıcının son konumunu almak için aşağıdaki kodu yazalım
            val lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            if (lastLocation != null){
                // Null değilse , haritamızı son konumda göstereceğiz.
                // Son konumu haritamızda gösteriyorum .
                val lastKnowLatLng = LatLng(lastLocation.latitude , lastLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastKnowLatLng).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnowLatLng ,15f))
            }
        }
    }

    // Aşağıdaki metot , kullanıcı bizden bir izin istediği zaman , kullanıcının cevabını bize dönüyor.
    // Kullanıcı Yes'e basıp ilk konum iznini verdiğinde ilk gerçekleşmesini istediğimiz işlemleri bu metodun altına yazıyoruz
    // Bu metot bir kere çalışmaktadır .
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == 1){
            if (grantResults.size > 0){
                // grantResults Dizisinde izinler depolanmaktadır.
                // Kullanıcı bir izin verdiyse eğer , bu dizi içerisindeki eleman sayısı artacaktır.
                // Bu yüzden .size > 0 yazdığımda , kullanıcı izni vermiş oluyor
                if (ContextCompat.checkSelfPermission(this , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                    // Kullanıcının izni dahilinde şimdi konumunu alabiliriz.
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER , 1 , 1f , locationListener)
                }


            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    // Kullanıcı haritaya uzun bir şekilde tıkladığı zaman adresi markere göndermek için aşağıdaki kodu yazıyoruz.
    // Kulllanıcı haritaya uzun bir süre bastığında gerçekleşmesini istediklerimizi yazalım
    val myListener = object  : GoogleMap.OnMapLongClickListener {
        override fun onMapLongClick(p0: LatLng?) {

            mMap.clear()

            val geocoder = Geocoder(this@MapsActivity , Locale.getDefault())

            if (p0 != null){
                var address = ""
                try {
                    val addressList = geocoder.getFromLocation(p0!!.latitude , p0!!.longitude , 1)
                    if (addressList != null && addressList.size > 0){
                        if (addressList[0].thoroughfare != null){

                            address += addressList[0].thoroughfare

                            if (addressList[0].subThoroughfare != null){
                                address += addressList[0].subThoroughfare
                            }
                        }
                    }

                }catch (e:Exception){
                    e.printStackTrace()
                }
                mMap.addMarker(MarkerOptions().position(p0).title(address))
            }
            else{
                Toast.makeText(applicationContext,"Try Again",Toast.LENGTH_LONG).show()
            }

        }
    }

}