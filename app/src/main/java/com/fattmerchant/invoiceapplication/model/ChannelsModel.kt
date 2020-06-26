package com.fattmerchant.invoiceapplication.model

import androidx.annotation.Nullable
import androidx.room.*
import com.fattmerchant.invoiceapplication.database.CategoryConverters
import com.fattmerchant.invoiceapplication.database.Converters

data class ChannelsModel(
    val `data`: DataChannel
)

data class DataChannel(
    val channels: List<ChannelData>
)
@Entity(tableName = "channel_data")
data class ChannelData(
    @PrimaryKey(autoGenerate = true)
    val ids:Int,
    @Embedded(prefix = "cover_asset")
    val coverAsset: CoverAssetChannel?,
    @Embedded(prefix = "icon_asset")
    val iconAsset: IconAsset?=IconAsset("",""),
    @ColumnInfo
    val id: String?,
    @TypeConverters(Converters::class)
     val latestMedia: List<LatestMedia>?,
    @ColumnInfo
    val mediaCount: Int?,
    @TypeConverters(Converters::class)
    val series: List<LatestMedia>?,
    @ColumnInfo
    val slug: String?,
    @Embedded(prefix = "data_episode")
    val data: DataEpisode?,
    @TypeConverters(CategoryConverters::class)
    val categories: List<Category>?,
    @ColumnInfo
    val title: String?
)
data class CoverAssetChannel(
    @ColumnInfo(name = "url")
    val url: String
)
data class IconAsset(
    @ColumnInfo
    val thumbnailUrl: String?,
    @ColumnInfo
    val url: String?
)

data class LatestMedia(
    @Embedded(prefix = "coverassetx")
    val coverAsset: CoverAssetX,

    val title: String,
    val type: String
)

data class Sery(
    val coverAsset: CoverAssetX,
    val id: String,
    val title: String,
    val type: String
)

data class CoverAssetX(
    val url: String
)

data class CoverAssetXX(
    val url: String
)

data class DataEpisode(
    @TypeConverters(CategoryConverters::class)
    val media: List<MediaData>
)

data class MediaData(
    @Embedded(prefix = "channel")
    val channel: ChannelDatass,
    @Embedded(prefix = "cover_assets")
    val coverAsset: CoverAssets,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val type: String
)

data class ChannelDatass(
    val title: String
)

data class CoverAssets(
    val url: String
)

data class DataCategory(
    val categories: List<Category>
)

data class Category(
    val name: String
)