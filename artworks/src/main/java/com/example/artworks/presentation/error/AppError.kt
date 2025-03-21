package com.example.artworks.presentation.error

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}