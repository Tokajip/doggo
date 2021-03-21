package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.data.repository.DoggoRepository
import javax.inject.Inject

class GetBreedUseCaseImpl @Inject constructor(
    private val repository: DoggoRepository
) : GetBreedUseCase {
    override suspend fun execute(breedName: String): List<String> {
        return repository.getBreed(breedName)
    }
}