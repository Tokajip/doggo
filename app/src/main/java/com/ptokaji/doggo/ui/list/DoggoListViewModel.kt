package com.ptokaji.doggo.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ptokaji.doggo.domain.usecase.GetAllBreedsUseCase
import com.ptokaji.doggo.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoggoListViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase,
    private val breedsUiMapper: BreedsUiMapper
) : ViewModel() {

    fun getDogList(): LiveData<Result<List<BreedsUiModel>>> = liveData {
        try {
            val allBreeds = getAllBreedsUseCase.getAllBreeds()
            val mappedBreeds = allBreeds.map {
                breedsUiMapper.mapDomainToUi(it)
            }.flatten()
            emit(Result.Success(mappedBreeds))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }

    }
}