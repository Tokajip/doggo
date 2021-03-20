package com.ptokaji.doggo.domain.mapper

import com.ptokaji.doggo.domain.model.BreedsEntity
import javax.inject.Inject

class BreedsDomainMapper @Inject constructor() {

    fun fromApiToDomain(api: Map<String, List<String>>): List<BreedsEntity> {
        return api.toList().map { BreedsEntity(it.first, it.second) }
    }
}