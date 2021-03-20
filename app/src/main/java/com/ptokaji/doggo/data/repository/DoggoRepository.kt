package com.ptokaji.doggo.data.repository

interface DoggoRepository {

    suspend fun getAllBreeds(): Map<String, List<String>>
}