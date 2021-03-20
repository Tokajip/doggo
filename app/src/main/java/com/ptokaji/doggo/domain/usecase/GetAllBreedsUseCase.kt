package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.domain.model.BreedsEntity

interface GetAllBreedsUseCase {

    suspend fun getAllBreeds(): List<BreedsEntity>
}