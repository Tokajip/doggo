package com.ptokaji.doggo.data

import com.ptokaji.doggo.data.model.BreedsListResponse
import com.ptokaji.doggo.data.model.DogImagesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DoggoApi {

    @GET("api/breeds/list/all")
    suspend fun getBreedsList(): BreedsListResponse

    @GET("/api/breed/{breed}/images")
    suspend fun getBreed(@Path("breed") breed: String): DogImagesResponse

    @GET("/api/breed/{breed}/{subBreed}/images")
    suspend fun getSubBreed(
        @Path("breed") breed: String,
        @Path("subBreed") subBreed: String
    ): DogImagesResponse
}