package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.data.repository.DoggoRepository
import javax.inject.Inject

class GetSubBreedUseCaseImpl @Inject constructor(
    private val repository: DoggoRepository
) : GetSubBreedUseCase {
    override suspend fun execute(breedName: String, subBreed: String): List<String> {
        return repository.getSubBreed(breedName, subBreed)
    }
}