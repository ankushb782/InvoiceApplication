package com.fattmerchant.invoiceapplication

import com.fattmerchant.invoiceapplication.model.*
import retrofit2.Call
import retrofit2.Response

class DataRepository(val netWorkApi: NetWorkApi) {

    fun getEpisodes(responseData: OnResponseData) {
        netWorkApi.getEpisodes().enqueue(object : retrofit2.Callback<ChannelData> {
            override fun onResponse(call: Call<ChannelData>, response: Response<ChannelData>) {
                responseData.onSuccess((response.body() as ChannelData))
            }

            override fun onFailure(call: Call<ChannelData>, t: Throwable) {
                responseData.onFailure()
            }
        })
    }

    fun getChannels(responseData: OnResponseDataChannel) {
        netWorkApi.getChannels().enqueue(object : retrofit2.Callback<ChannelsModel> {
            override fun onResponse(call: Call<ChannelsModel>, response: Response<ChannelsModel>) {
                responseData.onSuccess((response.body() as ChannelsModel))
            }

            override fun onFailure(call: Call<ChannelsModel>, t: Throwable) {
                responseData.onFailure()
            }
        })
    }

    fun getCategory(responseData: OnResponseDataCategory) {
        netWorkApi.getCategory().enqueue(object : retrofit2.Callback<CategoryModel> {
            override fun onResponse(call: Call<CategoryModel>, response: Response<CategoryModel>) {
                responseData.onSuccess((response.body() as CategoryModel))
            }

            override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                responseData.onFailure()
            }
        })
    }

    interface OnResponseData {
        fun onSuccess(data: ChannelData)
        fun onFailure()
    }

    interface OnResponseDataChannel {
        fun onSuccess(data: ChannelsModel)
        fun onFailure()
    }

    interface OnResponseDataCategory {
        fun onSuccess(data: CategoryModel)
        fun onFailure()
    }
}

