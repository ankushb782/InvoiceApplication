package com.fattmerchant.invoiceapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fattmerchant.invoiceapplication.view.HomeListFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frag_container, HomeListFragment()).commit()
    }
}
