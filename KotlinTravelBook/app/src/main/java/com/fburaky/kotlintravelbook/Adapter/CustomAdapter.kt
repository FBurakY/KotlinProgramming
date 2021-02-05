package com.fburaky.kotlintravelbook.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.fburaky.kotlintravelbook.Model.Place
import com.fburaky.kotlintravelbook.R
import kotlinx.android.synthetic.main.custom_list_row.view.*

class CustomAdapter(private val placeList:ArrayList<Place>, private val context:Activity) : ArrayAdapter<Place>(context, R.layout.custom_list_row, placeList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater
        val customview = layoutInflater.inflate(R.layout.custom_list_row,null , true)
        customview.listRowTextView.text = placeList.get(position).address
        return customview
    }
}