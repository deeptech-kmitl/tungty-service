package com.tungty.tungtyservice.DTO

data class ReqRegisterDTO (
    val name: String,
    val surname: String,
    val username: String,
    val password: String,
    val studentId: String,
    val faculty: String,
    val field: String,
    val year: String,
    val profileImg: String,
    )
