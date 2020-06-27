package com.fattmerchant.invoiceapplication.di

import com.fattmerchant.invoiceapplication.NetWorkApi
import com.google.gson.GsonBuilder
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Network module test configuration with mockserver url.
 */
fun configureNetworkModuleForTest(baseApi: String)
        = module{
    single {
        Retrofit.Builder()
            .baseUrl(baseApi)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
    factory{ get<Retrofit>().create(NetWorkApi::class.java) }
}