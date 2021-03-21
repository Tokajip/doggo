package com.ptokaji.doggo.domain.usecase

interface GetBreedUseCase {

    suspend fun execute(breedName: String): List<String>
}