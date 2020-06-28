package com.fattmerchant.invoiceapplication.app

import com.fattmerchant.invoiceapplication.MainApplication
import org.koin.dsl.module.Module

/**
 * Helps to configure required dependencies for Instru Tests.
 * Method provideDependency can be overrided and new dependencies can be supplied.
 */
class TestMainApp : MainApplication() {
    override fun provideDependency() = listOf<Module>()
}