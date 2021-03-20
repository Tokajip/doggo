package com.ptokaji.doggo.data

import com.ptokaji.doggo.data.model.BreedsListResponse
import retrofit2.http.GET

interface DoggoApi {

    @GET("api/breeds/list/all")
    suspend fun getBreedsList(): BreedsListResponse
}