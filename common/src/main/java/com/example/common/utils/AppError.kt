package com.example.common.utils

sealed class AppError {
    object NoDataError : AppError()
    object PartialDataError : AppError()
}