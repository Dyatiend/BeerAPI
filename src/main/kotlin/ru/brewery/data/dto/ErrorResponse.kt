package ru.brewery.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String) {

    companion object {

        val NOT_FOUND_RESPONSE = ErrorResponse("Not found")
        val BAD_REQUEST_RESPONSE = ErrorResponse("Invalid request")
    }
}
