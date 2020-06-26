package com.fattmerchant.invoiceapplication


import com.fattmerchant.invoiceapplication.model.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NetWorkApi{
    @GET("z5AExTtw")
    fun getEpisodes(): Call<ChannelData>

    @GET("Xt12uVhM")
    fun getChannels(): Call<ChannelsModel>

    @GET("A0CgArX3")
    fun getCategory(): Call<CategoryModel>


}