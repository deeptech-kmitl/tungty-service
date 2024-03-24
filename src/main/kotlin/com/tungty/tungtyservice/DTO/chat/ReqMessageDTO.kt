package com.tungty.tungtyservice.DTO.chat

data class ReqMessageDTO (
    val message: String,
    val partyId: String,
    val userId: String,
    val appointmentDate: String
)