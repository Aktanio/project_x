package com.example.countries

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}