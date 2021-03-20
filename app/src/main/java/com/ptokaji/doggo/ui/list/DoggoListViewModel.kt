package com.ptokaji.doggo.ui.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoggoListViewModel @Inject constructor() : ViewModel() {

    fun getDogList() = "sdas + ${System.currentTimeMillis()}"
}