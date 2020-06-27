package com.fattmerchant.invoiceapplication.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module.module

/**
 * Creates Mockwebserver instance for testing
 */
val MockWebServerDIPTest = module {

    factory {
        MockWebServer()
    }

}