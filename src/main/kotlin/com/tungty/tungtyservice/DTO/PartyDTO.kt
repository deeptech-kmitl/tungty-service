package com.tungty.tungtyservice.DTO;

data class PartyDTO (
    val partyId: String,
    val partyCode: String,
    val partyOwner: String,
    val partyName: String,
    val partyDescription: String,
    val partyType: String,
    val partyCategory: String,
    val appointmentDate: String, // Assuming date as string for simplicity
    val appointmentTime: String,
    val memberAmount: Int,
    val memberList: List<String>,
    val createDateTime: String, // Assuming date as string for simplicity
    val updateDateTime: String // Assuming date as string for simplicity
)
