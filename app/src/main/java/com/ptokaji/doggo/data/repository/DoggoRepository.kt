package com.ptokaji.doggo.data.repository

interface DoggoRepository {
    suspend fun getAllBreeds(): Map<String, List<String>>
    suspend fun getBreed(name: String): List<String>
    suspend fun getSubBreed(breed: String, subBreed: String): List<String>
}