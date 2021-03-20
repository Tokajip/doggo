package com.ptokaji.doggo.data.model

data class BreedsListResponse(
    val message: Map<String, List<String>>,
    val status: String
)