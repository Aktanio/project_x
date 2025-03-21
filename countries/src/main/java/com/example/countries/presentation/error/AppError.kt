package com.example.countries.presentation.error

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}