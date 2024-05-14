package com.my.di.utils

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val message: String
): java.io.Serializable
