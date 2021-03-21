package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.data.repository.DoggoRepository
import com.ptokaji.doggo.domain.mapper.BreedsDomainMapper
import com.ptokaji.doggo.domain.model.BreedsEntity
import javax.inject.Inject

class GetAllBreedsUseCaseImpl @Inject constructor(
    private val repository: DoggoRepository,
    private val mapper: BreedsDomainMapper
): GetAllBreedsUseCase {
    override suspend fun execute(): List<BreedsEntity> {
        val api = repository.getAllBreeds()
        return mapper.fromApiToDomain(api)
    }
}