package com.example.dogquiz.data.retrofit

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DogApiResponse(
    @Json(name = "message") val imageUrls: List<String>,
    val status: String,
)