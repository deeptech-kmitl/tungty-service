package com.tungty.tungtyservice.DTO

data class EntireChatDTO(
        val chatId: String,
        val messageId: String,
        val partyId: String,
        val memberUserId: String,
        val createDateTime: String, // Assuming date as string for simplicity
        val updateDateTime: String // Assuming date as string for simplicity
)
