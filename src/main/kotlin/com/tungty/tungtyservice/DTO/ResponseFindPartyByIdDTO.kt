package com.tungty.tungtyservice.dto

import java.sql.Time
import java.sql.Timestamp

data class ResponseFindPartyByIdDTO(
    var partyId:String?,
    var partyName:String? = null,
    var partyDescription: String? = null,
    var partyType: String? = null,
    var partyCatagory: String? = null,
    var appointmentDate: Timestamp? = null,
    var appointmantTime: Time?,
    var memberAmount: Int,
    var memberList: List<String>,
    var chatRoomId: String
)
