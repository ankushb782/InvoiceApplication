package com.fattmerchant.invoiceapplication

import android.content.Context
import com.fattmerchant.invoiceapplication.database.AppDatabase
import com.fattmerchant.invoiceapplication.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.Executors

class DataRepository(val netWorkApi: NetWorkApi) {

    fun getEpisodes(responseData: OnResponseData,context: Context) {

        netWorkApi.getEpisodes().enqueue(object : retrofit2.Callback<ChannelData> {
            override fun onResponse(call: Call<ChannelData>, response: Response<ChannelData>) {

                if(response.body()!=null) {
                    var listOfProduct = mutableListOf<ChannelData>()
                    listOfProduct.add(response.body() as ChannelData)
                    Executors.newSingleThreadExecutor().execute {
                        AppDatabase.getDatabase(context)?.postDao()?.deleteAll()
                        for (i in 0 until listOfProduct.size) {
                            AppDatabase.getDatabase(context)?.postDao()
                                ?.insertAll(listOfProduct.get(i))
                        }

                    }
                    responseData.onSuccess((response.body() as ChannelData))
                }else{
                    responseData.onFailure(getErrorMessage(response.errorBody()))
                }
            }

            override fun onFailure(call: Call<ChannelData>, t: Throwable) {
                responseData.onFailure(t.message)
            }
        })
    }

    fun getChannels(responseData: OnResponseDataChannel,context: Context) {
        netWorkApi.getChannels().enqueue(object : retrofit2.Callback<ChannelsModel> {
            override fun onResponse(call: Call<ChannelsModel>, response: Response<ChannelsModel>) {
                if(response.body()!=null) {
                var listOfProduct = mutableListOf<ChannelData>()
                listOfProduct.addAll(response.body()?.data?.channels!!)
                Executors.newSingleThreadExecutor().execute {
                    for(i in 0 until listOfProduct.size){
                        AppDatabase.getDatabase(context)?.postDao()?.insertAll(listOfProduct.get(i))
                    }
                }
                responseData.onSuccess((response.body() as ChannelsModel))
            }else{
                    responseData.onFailure(getErrorMessage(response.errorBody()))
            }
            }

            override fun onFailure(call: Call<ChannelsModel>, t: Throwable) {
                responseData.onFailure(t.message)
            }
        })
    }

    fun getCategory(responseData: OnResponseDataCategory,context: Context) {
        netWorkApi.getCategory().enqueue(object : retrofit2.Callback<CategoryModel> {
            override fun onResponse(call: Call<CategoryModel>, response: Response<CategoryModel>) {
                if(response.body()!=null) {
                var listOfProduct = mutableListOf<ChannelData>()
                var iconAsset:IconAsset= IconAsset("","")
                var channelData:ChannelData= ChannelData(0,null,iconAsset,"",
                    emptyList(),0, emptyList(), "",null,response.body()?.data?.categories,"")
                listOfProduct.add(channelData)
                Executors.newSingleThreadExecutor().execute {
                    for(i in 0 until listOfProduct.size){
                        AppDatabase.getDatabase(context)?.postDao()?.insertAll(listOfProduct.get(i))
                    }
                }
                responseData.onSuccess((response.body() as CategoryModel))
                }else{
                    responseData.onFailure(getErrorMessage(response.errorBody()))
                }
            }

            override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                responseData.onFailure(t.message)
            }
        })
    }

    interface OnResponseData {
        fun onSuccess(data: ChannelData)
        fun onFailure(message: String?)
    }

    interface OnResponseDataChannel {
        fun onSuccess(data: ChannelsModel)
        fun onFailure(message: String?)
    }

    interface OnResponseDataCategory {
        fun onSuccess(data: CategoryModel)
        fun onFailure(message: String?)
    }

    fun getErrorMessage(errorBody: ResponseBody?):String {
        val gson = Gson()
        val type = object : TypeToken<ErrorResponse>() {}.type
        var errorResponse: ErrorResponse? = gson.fromJson(errorBody!!.charStream(), type)
       return errorResponse?.message.toString()
    }
}

