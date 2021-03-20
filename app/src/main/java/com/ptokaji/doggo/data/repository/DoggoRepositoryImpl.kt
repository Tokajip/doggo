package com.ptokaji.doggo.data.repository

import com.ptokaji.doggo.data.DoggoApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoggoRepositoryImpl @Inject constructor(
    private val api: DoggoApi
) : DoggoRepository {
    override suspend fun getAllBreeds(): Map<String, List<String>> = withContext((Dispatchers.IO)) {
        api.getBreedsList().message
    }
}