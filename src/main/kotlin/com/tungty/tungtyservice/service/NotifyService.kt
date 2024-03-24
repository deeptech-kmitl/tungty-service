package com.tungty.tungtyservice.service

import com.tungty.tungtyservice.DTO.notify.ReqAddNotifyDTO
import com.tungty.tungtyservice.entity.NotifyEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface NotifyService {
    fun findAllNotify(): Flux<NotifyEntity>
    fun findAllByUserIdNotify(userId: String): Flux<NotifyEntity>
    fun addNotify(notifydata: ReqAddNotifyDTO): String
    fun updateStatus(notifyId: String, status: String): String
    fun deleteNotify(notifyId: String): String

    fun findNotifyById(notifyId: String): Mono<NotifyEntity>
}