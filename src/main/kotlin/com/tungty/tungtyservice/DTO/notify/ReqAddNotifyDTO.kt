package com.tungty.tungtyservice.DTO.notify

data class ReqAddNotifyDTO(
    val partyName: String,
    val typeAction: String,
    val notifyDescription: String,
    val userId: String,
    val appointmentDate: String,
    val appointmentTime: String,
    val status: String,
    val partyId: String
)
