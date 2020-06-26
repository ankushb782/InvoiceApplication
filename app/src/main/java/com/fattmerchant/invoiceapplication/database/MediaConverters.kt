package com.fattmerchant.invoiceapplication.database

import androidx.room.TypeConverter
import com.fattmerchant.invoiceapplication.model.Category
import com.fattmerchant.invoiceapplication.model.LatestMedia
import com.fattmerchant.invoiceapplication.model.MediaData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MediaConverters {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<MediaData?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<MediaData?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<MediaData>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<MediaData?>?>() {}.type
        return gson.fromJson<List<MediaData>>(countryLangString, type)
    }
}