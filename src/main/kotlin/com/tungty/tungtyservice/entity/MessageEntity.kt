package com.tungty.tungtyservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

// Message Entity
@Document(collection = "MessageCollection")
data class MessageEntity(
        @Id
        val _id: String,
        val partyId: String,
        val userId: String,
        val message: String,
        val appointmentDate: String,
        val username: String,
        val profileImg: String
)
