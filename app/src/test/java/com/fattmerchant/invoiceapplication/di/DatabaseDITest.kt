package com.fattmerchant.invoiceapplication.di

import androidx.room.Room
import com.fattmerchant.invoiceapplication.database.AppDatabase
import org.koin.dsl.module.module

fun configureDatabaseModuleForTest()
        = module{

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "channels_db_test").build()
    }

    single { get<AppDatabase>().postDao() }
}