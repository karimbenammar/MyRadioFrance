package com.example.myradiofrance.domain.util

sealed class Resource<out L, out R> {
    data class Success<out L>(val a: L) : Resource<L, Nothing>()
    data class Error<out R>(val b: R) : Resource<Nothing, R>()

    val isError: Boolean
        get() = this is Error<R>

    val isSuccess: Boolean
        get() = this is Success<L>
}

fun <L> success(a: L) = Resource.Success(a)
fun <R> error(b: R) = Resource.Error(b)

fun <T, L, R> Resource<L, R>.fold(fnL: (L) -> T, fnR: (R) -> T): T = when (this) {
    is Resource.Success -> fnL(a)
    is Resource.Error -> fnR(b)
}

suspend fun <T, L, R> Resource<L, R>.coFold(fnL: (L) -> T, fnR: suspend (R) -> T): T = when (this) {
    is Resource.Success -> fnL(a)
    is Resource.Error -> fnR(b)
}