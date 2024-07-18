package com.example.network

sealed class ErrorEntity {

    data object Network : ErrorEntity()

    data object NotFound : ErrorEntity()

    data object AccessDenied : ErrorEntity()

    data object ServiceUnavailable : ErrorEntity()

    data object Unknown : ErrorEntity()
}