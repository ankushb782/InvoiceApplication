package com.fattmerchant.invoiceapp


import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fattmerchant.invoiceapplication.DataRepository
import com.fattmerchant.invoiceapplication.model.*
import org.koin.core.KoinContext
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject


class CommonViewModel() : ViewModel(), KoinComponent {
    val dataRepository: DataRepository by inject()

    var listOfEpisodes = MutableLiveData<List<ChannelData>>()
    var listOfChannels = MutableLiveData<List<ChannelData>>()
    var listOfCategory = MutableLiveData<List<ChannelData>>()
    var errorData = MutableLiveData<String>()

    init {
        listOfEpisodes.value = listOf()
        listOfChannels.value = listOf()
        listOfCategory.value = listOf()
    }

    fun getEpisodes(context:Context) {
        dataRepository.getEpisodes(object : DataRepository.OnResponseData {
            override fun onSuccess(data: ChannelData) {
                var listOfProduct = mutableListOf<ChannelData>()
                listOfProduct.add(data)
                listOfEpisodes.value = listOfProduct

                getChannels(context)

            }

            override fun onFailure(message: String?) {
                errorData.value=message

            }
        },context)
    }

    fun getChannels(context:Context) {
        dataRepository.getChannels(object : DataRepository.OnResponseDataChannel {
            override fun onSuccess(data: ChannelsModel) {
                var listOfProduct = mutableListOf<ChannelData>()
                listOfProduct.addAll(data.data.channels)
                listOfChannels.value = listOfProduct

                getCategory(context)
            }
            override fun onFailure(message: String?) {
                errorData.value=message

            }
        },context)
    }
    fun getCategory(context:Context) {
        dataRepository.getCategory(object : DataRepository.OnResponseDataCategory {
            override fun onSuccess(data: CategoryModel) {
                var listOfProduct = mutableListOf<ChannelData>()
                var iconAsset:IconAsset= IconAsset("","")
                var channelData:ChannelData= ChannelData(0,null,iconAsset,"",
                    emptyList(),0, emptyList(), "",null,data.data.categories,"")
                listOfProduct.add(channelData)
                listOfCategory.value = listOfProduct

            }

            override fun onFailure(message: String?) {
                errorData.value=message

            }
        },context)
    }





}