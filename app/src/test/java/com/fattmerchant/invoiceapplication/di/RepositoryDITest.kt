package com.fattmerchant.invoiceapplication.di

import com.fattmerchant.invoiceapplication.DataRepository
import org.koin.dsl.module.module


val RepoDependency = module {

    factory {
        DataRepository()
    }
}