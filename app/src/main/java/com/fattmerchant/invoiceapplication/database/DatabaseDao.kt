package com.fattmerchant.invoiceapplication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fattmerchant.invoiceapplication.model.ChannelData

@Dao
 interface DatabaseDao {
    @Query("SELECT * FROM channel_data")
    fun all(): List<ChannelData>



    @Insert
    fun insertAll(vararg users: ChannelData)



    @Query("DELETE FROM channel_data")
    fun deleteAll()


}