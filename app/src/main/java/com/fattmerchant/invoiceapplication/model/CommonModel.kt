package com.fattmerchant.invoiceapplication.model

data class CommonModel(
    val data: DataEpisode,
    val channels: List<ChannelData>,
    val categories: DataCategory?
)

