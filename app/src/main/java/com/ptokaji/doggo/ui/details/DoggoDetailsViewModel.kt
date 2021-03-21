package com.ptokaji.doggo.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ptokaji.doggo.domain.usecase.GetBreedUseCase
import com.ptokaji.doggo.domain.usecase.GetSubBreedUseCase
import com.ptokaji.doggo.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoggoDetailsViewModel @Inject constructor(
    private val getBreedUseCase: GetBreedUseCase,
    private val getSubBreedUseCase: GetSubBreedUseCase
): ViewModel() {

    fun getImages(breed: String, subBreed: String?) = liveData {
        try {
            val imageList = if (subBreed != null) {
                getSubBreedUseCase.execute(breed, subBreed)
            } else {
                getBreedUseCase.execute(breed)
            }
            emit(Result.Success(imageList))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }

    }
}