package com.luke.codejpmc.repository

sealed class Results<out T> {
    data class Ok<T>(
        val data :T
    ):Results<T>()

    data class Error<T>(
        val exception:Throwable
    ):Results<T>()
}
