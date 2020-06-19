package com.example.softxpert.task.cars.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Car(
    @Json(name = "id") val id: String?,
    @Json(name = "brand") val brand: String?,
    @Json(name = "constructionYear") val constructionYear: String?,
    @Json(name = "isUsed") val isUsed: Boolean?,
    @Json(name = "imageUrl") val imageUrl: String?
) : Serializable