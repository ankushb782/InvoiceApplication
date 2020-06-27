package com.fattmerchant.invoiceapplication.di

import android.content.Context
import androidx.room.Room
import com.fattmerchant.invoiceapplication.DataRepository
import com.fattmerchant.invoiceapplication.database.AppDatabase
import io.mockk.mockk
import io.reactivex.schedulers.Schedulers.single

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 2) Network
 * 3) Repository
 * 4) Database
 */
fun configureTestAppComponent(baseApi: String)
        = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    RepoDependency,
    configureDatabaseModuleForTest()
)
