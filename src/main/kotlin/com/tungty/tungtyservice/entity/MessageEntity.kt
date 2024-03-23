package com.tungty.tungtyservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Timestamp

// Message Entity
@Document(collection = "MessageCollection")
data class MessageEntity(
        @Id
        val _id: String,
        val partyId: String,
        val userId: String,
        val content: String,
        val createDate: String
)
