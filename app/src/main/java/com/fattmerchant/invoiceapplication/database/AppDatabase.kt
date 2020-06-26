package com.fattmerchant.invoiceapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fattmerchant.invoiceapplication.database.DatabaseDao
import com.fattmerchant.invoiceapplication.model.ChannelData

@Database(entities = [ChannelData::class], version = 1)
@TypeConverters(Converters::class,CategoryConverters::class,MediaConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): DatabaseDao

    //abstract val appDatabase:AppDatabase
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            synchronized(this) {
            if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "channels_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}