package com.example.myradiofrance.domain.util

sealed class Failure(val message: String) {
    class NetworkError(message: String) : Failure(message)
}