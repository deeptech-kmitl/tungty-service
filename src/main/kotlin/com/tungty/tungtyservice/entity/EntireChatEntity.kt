package com.tungty.tungtyservice.entity


// EntireChat Entity
@Entity
data class EntireChatEntity(
        @Id
        val chatId: String,
        val messageId: String,
        val partyId: String,
        val memberUserId: String,
        val createDateTime: Date,
        val updateDateTime: Date
)
