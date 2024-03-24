package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.notify.ReqAddNotifyDTO
import com.tungty.tungtyservice.entity.NotifyEntity
import com.tungty.tungtyservice.job.NotifyJob
import com.tungty.tungtyservice.service.NotifyService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.sql.DriverManager

@RestController
@RequestMapping("/notify")
class NotifyController {

    @Autowired
    lateinit var notifyService: NotifyService

//    @Autowired
//    lateinit var notifyJob: NotifyJob

    @GetMapping("/getAll/{userId}")
    fun getAllNotify(@PathVariable userId: String): Flux<NotifyEntity> {
        return notifyService.findAllByUserIdNotify(userId)
    }

    @GetMapping("/getAll")
    fun getAll(): Flux<NotifyEntity>{
        System.out.println("This is an informational message");
        return notifyService.findAllNotify()
    }

    @GetMapping("/{notifyId}")
    fun getNotify(@PathVariable notifyId: String): Mono<NotifyEntity> {
        return notifyService.findNotifyById(notifyId)
    }

    @PutMapping("/updateStatus/{notifyId}/{status}")
    fun updateNotify(@PathVariable notifyId: String, @PathVariable status: String): String{
        return notifyService.updateStatus(notifyId, status)
    }

    @PostMapping("/createNotify")
    fun createNotify(@RequestBody body: ReqAddNotifyDTO): String{
        // using this service when edit party, delete party and create party
        // they gonna set job in this service too
        return notifyService.addNotify(body)
    }

    @DeleteMapping("/delete/{notifyId}")
    fun deleteNotify(@PathVariable notifyId: String): String{
        return notifyService.deleteNotify(notifyId)
    }

}