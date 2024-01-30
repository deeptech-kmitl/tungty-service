package com.tungty.tungtyservice.DTO

data class MessageDTO(
        val messageId: String,
        val messageContent: String,
        val senderUserId: String,
        val timestamp: String, // Assuming date as string for simplicity
        val createDateTime: String // Assuming date as string for simplicity
)
