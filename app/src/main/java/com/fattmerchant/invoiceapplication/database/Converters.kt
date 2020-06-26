package com.fattmerchant.invoiceapplication.database

import androidx.room.TypeConverter
import com.fattmerchant.invoiceapplication.model.LatestMedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<LatestMedia?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<LatestMedia?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<LatestMedia>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<LatestMedia?>?>() {}.type
        return gson.fromJson<List<LatestMedia>>(countryLangString, type)
    }
}