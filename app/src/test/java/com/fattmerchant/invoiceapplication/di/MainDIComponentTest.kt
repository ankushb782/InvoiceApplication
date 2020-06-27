package com.fattmerchant.invoiceapplication.di

import com.fattmerchant.invoiceapplication.DataRepository

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Usecase
 * 3) Repository
 */
fun configureTestAppComponent(baseApi: String)
        = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    RepoDependency
)
