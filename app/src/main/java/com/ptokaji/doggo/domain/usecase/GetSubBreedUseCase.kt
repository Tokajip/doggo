package com.ptokaji.doggo.domain.usecase

interface GetSubBreedUseCase {
    suspend fun execute(breedName: String, subBreed: String): List<String>
}