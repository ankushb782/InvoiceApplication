package com.fattmerchant.invoiceapplication.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module.module

/**
 * Mock web server Koin DI component for Instrumentation Testing
 */
val MockWebServerInstrumentationTest = module {

    factory {
        MockWebServer()
    }
}