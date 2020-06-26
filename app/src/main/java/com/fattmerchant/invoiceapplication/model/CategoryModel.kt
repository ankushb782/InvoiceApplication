package com.fattmerchant.invoiceapplication.model

data class CategoryModel(
    val `data`: DataCategory
)

data class CategoryData(
    val categories: List<Category>
)

data class Categorys(
    val name: String
)