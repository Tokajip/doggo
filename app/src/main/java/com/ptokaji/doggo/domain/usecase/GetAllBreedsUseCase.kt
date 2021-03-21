package com.ptokaji.doggo.domain.usecase

import com.ptokaji.doggo.domain.model.BreedsEntity

interface GetAllBreedsUseCase {

    suspend fun execute(): List<BreedsEntity>
}