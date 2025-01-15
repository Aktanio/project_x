package com.example.a2.data.db

sealed class AppError {
    object NoInternetError : AppError()
}