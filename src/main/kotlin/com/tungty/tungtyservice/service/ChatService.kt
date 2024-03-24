package com.tungty.tungtyservice.service

import com.tungty.tungtyservice.DTO.chat.ReqMessageDTO
import com.tungty.tungtyservice.entity.MessageEntity
import com.tungty.tungtyservice.entity.PartyEntity
import reactor.core.publisher.Flux

interface ChatService {
    fun findAllMessage(partyId: String): Flux<MessageEntity>

    fun findAllChatByUserId(userId: String): Flux<PartyEntity>

    fun addMessage(message: ReqMessageDTO): String

}