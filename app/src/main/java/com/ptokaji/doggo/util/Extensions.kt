package com.ptokaji.doggo.util

fun String.getFormattedBreedName(subBreed: String?): String {
    val title = StringBuilder(this.capitalize())
    subBreed?.let {
        title.insert(0, it.capitalize())
        title.insert(it.length, " ")
    }
    return title.toString()
}