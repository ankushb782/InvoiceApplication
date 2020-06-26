package com.fattmerchant.invoiceapplication.model

data class EpisodeModel(
    val `data`: DataEpisode
)

data class DataEpisodes(
    val media: List<Media>
)

data class Media(
    val channel: Channel,
    val coverAsset: CoverAsset,
    val title: String,
    val type: String
)

data class Channel(
    val title: String
)

data class CoverAsset(
    val url: String
)