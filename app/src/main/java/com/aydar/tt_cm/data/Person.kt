package com.aydar.tt_cm.data

data class Person(
    val id: Long = counter++,
    val name: String,
    val city: String,
    val url: String
) {
    companion object {
        private var counter = 0L
    }
}