package com.fattmerchant.invoiceapplication.di

import com.fattmerchant.invoiceapp.CommonViewModel
import org.koin.android.viewmodel.ext.koin.viewModel

/**
 * Main Koin DI component for Instrumentation Testing
 */
fun generateTestAppComponent(baseApi: String)
        = listOf(
    configureNetworkForInstrumentationTest(baseApi),
    MockWebServerInstrumentationTest,
    RepoDependencyForInstrumentationTest,
    configureDatabaseModuleForInstrumentationTest(),
    configureViewModelInstrumentationDI()
)