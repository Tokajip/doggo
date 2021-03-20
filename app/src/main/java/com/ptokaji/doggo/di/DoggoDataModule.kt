package com.ptokaji.doggo.di

import com.google.gson.GsonBuilder
import com.ptokaji.doggo.data.DoggoApi
import com.ptokaji.doggo.data.repository.DoggoRepository
import com.ptokaji.doggo.data.repository.DoggoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class DoggoDataModule {

    @Binds
    abstract fun bindDoggoRepository(impl: DoggoRepositoryImpl): DoggoRepository

    companion object {
        @Provides
        fun provideApi(): DoggoApi {
            val interceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            val gson = GsonBuilder()
                .setLenient()
                .create()

            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://dog.ceo/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return retrofit
                .create(DoggoApi::class.java)

        }
        @Provides
        @DispatchersIO
        fun provideIoDispatchers(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}