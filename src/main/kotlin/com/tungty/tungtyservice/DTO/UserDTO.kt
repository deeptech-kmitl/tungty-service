package com.tungty.tungtyservice.DTO

data class UserDTO(
        val userId: String,
        val name: String,
        val surname: String,
        val password: String,
        val studentId: String,
        val faculty: String,
        val field: String,
        val year: Int,
        val profileImg: String,
        val createDateTime: String, // Assuming date as string for simplicity
        val updateDateTime: String // Assuming date as string for simplicity
)
