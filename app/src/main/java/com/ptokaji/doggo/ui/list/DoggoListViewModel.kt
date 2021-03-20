package com.ptokaji.doggo.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ptokaji.doggo.domain.model.BreedsEntity
import com.ptokaji.doggo.domain.usecase.GetAllBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoggoListViewModel @Inject constructor(
    private val getAllBreedsUseCase: GetAllBreedsUseCase
) : ViewModel() {

    fun getDogList(): LiveData<List<BreedsEntity>> = liveData {
        val allBreeds = getAllBreedsUseCase.getAllBreeds()
        emit(allBreeds)
    }
}