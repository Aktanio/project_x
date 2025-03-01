package com.example.artworks

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}