package com.example.a2.data.db

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}