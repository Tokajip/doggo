package com.ptokaji.doggo.data.repository

import com.ptokaji.doggo.data.DoggoApi
import com.ptokaji.doggo.di.DispatchersIO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DoggoRepositoryImpl @Inject constructor(
    private val api: DoggoApi,
    @DispatchersIO private val ioDispatcher: CoroutineDispatcher
) : DoggoRepository {
    override suspend fun getAllBreeds(): Map<String, List<String>> = withContext(ioDispatcher) {
        api.getBreedsList().message
    }

    override suspend fun getBreed(name: String): List<String> = withContext(ioDispatcher) {
        api.getBreed(name).message
    }

    override suspend fun getSubBreed(breed: String, subBreed: String): List<String> =
        withContext(ioDispatcher) { api.getSubBreed(breed, subBreed).message }
}