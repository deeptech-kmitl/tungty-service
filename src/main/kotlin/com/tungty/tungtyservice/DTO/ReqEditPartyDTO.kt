package com.tungty.tungtyservice.dto

import java.sql.Time
import java.sql.Timestamp

data class ReqEditPartyDTO(
    var partyId: String,
    var partyName: String,
    var partyDescription: String,
    var partyType: String,
    var partyCatagory: String,
    var appointmentDate: Timestamp,
    var appointmantTime: Time?,
)
