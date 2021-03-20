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
}