package com.fattmerchant.invoiceapplication.database

import androidx.room.TypeConverter
import com.fattmerchant.invoiceapplication.model.Category
import com.fattmerchant.invoiceapplication.model.LatestMedia
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class CategoryConverters {
    @TypeConverter
    fun fromCountryLangList(countryLang: List<Category?>?): String? {
        if (countryLang == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Category?>?>() {}.type
        return gson.toJson(countryLang, type)
    }

    @TypeConverter
    fun toCountryLangList(countryLangString: String?): List<Category>? {
        if (countryLangString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Category?>?>() {}.type
        return gson.fromJson<List<Category>>(countryLangString, type)
    }
}