package com.ptokaji.doggo.di

import com.ptokaji.doggo.domain.usecase.GetAllBreedsUseCase
import com.ptokaji.doggo.domain.usecase.GetAllBreedsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DoggoDomainModule {

    @Binds
    abstract fun bindGetAllBreedsUseCase(impl: GetAllBreedsUseCaseImpl): GetAllBreedsUseCase
}