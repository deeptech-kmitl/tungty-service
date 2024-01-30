package com.tungty.tungtyservice.dto

data class ResponseLoginDTO (
    var errorMessage: String? = null,
    var taken: String? = null,
    var userId: String? = null,
)

