package com.tungty.tungtyservice.DTO


data class ResponseGetMyProfileDTO(
    var userId: String,
    var username: String,
    var name: String,
    var surname: String,
    var faculty: String,
    var field: String,
    var year: String
)
