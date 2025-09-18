package com.example.dogquiz.data

import android.util.Log
import com.example.dogquiz.data.retrofit.DogApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class DogQuizRepository {
    private val api: DogApiService by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(DogApiService::class.java)
    }

    suspend fun getDogImages(): List<String> {
        try {
            val response = api.getFourRandomDogImages()

            if (response.isSuccessful) {
                return response.body()?.imageUrls ?: emptyList()
            } else {
                Log.e("DogQuizViewModel", "API call failed with code: ${response.code()}")
                return emptyList()
            }
        } catch (e: Exception) {
            Log.e("DogQuizViewModel", "Network error: ${e.message}", e)
            return emptyList()
        }
    }
}