package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.entity.MessageEntity
import com.tungty.tungtyservice.service.ChatService
import com.tungty.tungtyservice.service.TestService
import com.tungty.tungtyservice.service.addService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/test")
class testController {

    @Autowired
    lateinit var testService: TestService

    @Autowired
    lateinit var addService: addService

    @Autowired
    lateinit var chatService: ChatService

    @GetMapping("say")
    fun say(): String{
        return testService.testSay()
    }

    @GetMapping("say/{someting}")
    fun saySometimg(@PathVariable("someting") word: String): String{
        return testService.testSaySometing(word)
    }

    @GetMapping("add/{number}")
    fun addNumber(@PathVariable("number") number: Int): String{
        return addService.addNumber(number)
    }

//    @GetMapping("messages")
//    fun findAllMessage(): Flux<MessageEntity> {
//        return chatService.findAllMessage()
//    }

}