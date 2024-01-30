package com.tungty.tungtyservice.entity

// Message Entity
@Entity
data class MessageEntity(
        @Id
        val messageId: String,
        val messageContent: String,
        val senderUserId: String,
        val timestamp: Date,
        val createDateTime: Date
)
