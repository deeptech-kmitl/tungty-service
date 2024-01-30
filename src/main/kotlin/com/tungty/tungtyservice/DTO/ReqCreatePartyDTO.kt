package com.tungty.tungtyservice.dto

import com.tungty.tungtyservice.DTO.UserDTO
import java.sql.Time
import java.sql.Timestamp

data class ReqCreatePartyDTO(
    var partyName: String,
    var partyDescription: String,
    var partyType: String,
    var partyCatagory: String,
    var appointmentDate: Timestamp,
    var appointmantTime: Time?,
    var memberAmount: Int,
    var memberList: List<UserDTO>
)
