package com.tungty.tungtyservice.DTO

data class ResponseLoginDTO(
        val accessToken: String,
        val userId: String,
        val username: String
)
