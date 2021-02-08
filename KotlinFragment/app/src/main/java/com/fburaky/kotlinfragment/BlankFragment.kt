package com.fburaky.kotlinfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        /*
          inflater ne zaman kullanıyorduk ?
          Ne zaman ki bir layout'u bir xml ile bağlamak istediğimizde karşımıza bu yapı çıkmaktadır.
          Daha önceden yaptığım gibi menu inflater da olabilir , layout inflater da olabilir .
          Xml'de oluşturduğum bir yapıyla buradaki bir yapıyla bağladığımız bir yardımcı yapıdır Inflater.
          + inflater.inflate(R.layout.fragment_blank, container, false) -> Yandaki kodda
          + fragment_blank.xml buraya bağlanmış .
         */
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }
}