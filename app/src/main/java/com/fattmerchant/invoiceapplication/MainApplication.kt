package com.fattmerchant.invoiceapplication

import android.app.Application
import org.koin.android.ext.android.startKoin

open class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initiateKoin()
    }

    private fun initiateKoin() {
        startKoin(this,
            provideDependency(),
            loadPropertiesFromFile = true)
    }

    open fun provideDependency() = listOf(mainModule)
}