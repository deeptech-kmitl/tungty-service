package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.chat.ReqMessageDTO
import com.tungty.tungtyservice.DTO.party.PartyDTO
import com.tungty.tungtyservice.entity.MessageEntity
import com.tungty.tungtyservice.entity.PartyEntity
import com.tungty.tungtyservice.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/chat")
class ChatController {

    @Autowired
    lateinit var chatService: ChatService
    @GetMapping("/getAllChat/{userId}")
    fun findChatByUserId(@PathVariable userId: String): Flux<PartyEntity>{
        System.out.println("userId: $userId")
        return chatService.findAllChatByUserId(userId)
    }

    @GetMapping("/getAllMessage/{partyId}")
    fun findMessageByPartyId(@PathVariable partyId: String): Flux<MessageEntity>{
        System.out.println(partyId)
        return chatService.findAllMessage(partyId)
    }

    @PostMapping("/createMessage")
    fun addMessage(@RequestBody body: ReqMessageDTO): String{
        return chatService.addMessage(body)
    }
}