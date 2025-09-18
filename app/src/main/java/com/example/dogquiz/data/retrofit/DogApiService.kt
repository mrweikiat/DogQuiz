package com.example.dogquiz.data.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface DogApiService {
    @GET("breeds/image/random/4")
    suspend fun getFourRandomDogImages(): Response<DogApiResponse>
}