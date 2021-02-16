package com.fburaky.kotlinworkmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class RefreshDatabase(val context: Context, workerParams: WorkerParameters) : Worker(context,
    workerParams
) {
    override fun doWork(): Result {
        // Aşağıdaki kodumuzda , MainAktivitemizde oluşturduğumuz datayı alabiliyoruz.
        val getData = inputData
        val myNumber = getData.getInt("intKey",0)
        //Work-Manager içerisinde ne yapmak istersek bu metodun içinde yazıyoruz.
        refreshDatabase(myNumber)
        return Result.failure()
    }

    private fun refreshDatabase(myNumber : Int){
        val sharedPreferences = context.getSharedPreferences("com.fburaky.kotlinworkmanager",Context.MODE_PRIVATE)
        var mySavedNumber = sharedPreferences.getInt("myNumber",0)
        mySavedNumber = mySavedNumber + myNumber
        println(mySavedNumber)
        sharedPreferences.edit().putInt("myNumber",mySavedNumber).apply()
    }
}