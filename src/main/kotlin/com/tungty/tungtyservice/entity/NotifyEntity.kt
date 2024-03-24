package com.tungty.tungtyservice.entity

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id


@Document(collection = "NotifyCollection")
data class NotifyEntity(
    @Id
    val _id: String,
    val partyName: String,
    val typeAction: String,
    val notifyDescription: String,
    val userId: String,
    val appointmentDate: String,
    val appointmentTime: String,
    var status: String,
    val partyId: String
)
