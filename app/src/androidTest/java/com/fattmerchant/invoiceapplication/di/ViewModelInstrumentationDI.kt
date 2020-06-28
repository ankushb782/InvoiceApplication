package com.fattmerchant.invoiceapplication.di

import com.fattmerchant.invoiceapp.CommonViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

fun configureViewModelInstrumentationDI() = module {
    viewModel { CommonViewModel() }
}