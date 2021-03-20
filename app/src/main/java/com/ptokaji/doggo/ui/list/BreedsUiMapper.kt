package com.ptokaji.doggo.ui.list

import com.ptokaji.doggo.domain.model.BreedsEntity
import timber.log.Timber
import javax.inject.Inject

class BreedsUiMapper @Inject constructor() {

    fun mapDomainToUi(domain: BreedsEntity): List<BreedsUiModel> {
        return if (domain.subBreeds.isEmpty()) {
            listOf(BreedsUiModel(domain.name){
                Timber.d("OnItemClicked data=$it")
            })
        } else {
            domain.subBreeds.map {
                BreedsUiModel(domain.name, it) {
                    Timber.d("OnItemClicked data=$it")
                }
            }
        }
    }
}