package com.fattmerchant.invoiceapplication

import com.fattmerchant.invoiceapp.CommonViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val mainModule = module {

    single { DataRepository() }

    single { createWebService() }

    viewModel { CommonViewModel() }

}

fun createWebService(): NetWorkApi {
    var YOUR_TOKEN = ""
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://pastebin.com/raw/")
        .client(OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request().newBuilder()
              //  .addHeader("Authorization", "Bearer ${YOUR_TOKEN}")
                .addHeader("Content-Type", "application/json")
                .addHeader("Content-type", "application/json")
              //  .addHeader("Postman-Token", "ade6e3b1-3ef0-4bc1-a038-a2fd8655097e")
                .addHeader("cache-control", "no-cache")
                .build()
            chain.proceed(request)
        }.build())
        .build()

    return retrofit.create(NetWorkApi::class.java)
}