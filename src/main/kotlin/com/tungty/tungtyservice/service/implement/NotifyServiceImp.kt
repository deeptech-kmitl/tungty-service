package com.tungty.tungtyservice.service.implement

import com.tungty.tungtyservice.DTO.notify.ReqAddNotifyDTO
import com.tungty.tungtyservice.entity.NotifyEntity
import com.tungty.tungtyservice.repository.NotifyRepository
import com.tungty.tungtyservice.service.NotifyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class NotifyServiceImp: NotifyService {
    @Autowired
    lateinit var notifyRepository: NotifyRepository

    override fun addNotify(notifydata: ReqAddNotifyDTO): String {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)
            val id = UUID.randomUUID().toString()
            val notifyAddData = NotifyEntity(
                _id = id,
                partyName = notifydata.partyName,
                typeAction = notifydata.typeAction,
                notifyDescription = notifydata.notifyDescription,
                userId = notifydata.userId,
                appointmentDate = notifydata.appointmentDate,
                appointmentTime = notifydata.appointmentTime,
                status = notifydata.status,
                partyId = notifydata.partyId
            )
            val result = notifyRepository.save(notifyAddData)

            // will set schedule noti job in here soon if addNotify is type notify

            return result.block().toString()
        }catch (e: Exception){
            return e.toString()
        }


    }

    override fun findAllNotify(): Flux<NotifyEntity> {
        return notifyRepository.findAll()
    }

    override fun findAllByUserIdNotify(userId: String): Flux<NotifyEntity> {
        return  notifyRepository.findAllByUserId(userId)
    }

    override fun updateStatus(notifyId: String, status: String): String {
        try {
            var datanotify = notifyRepository.findById(notifyId).block()
            datanotify?.status = status
            val result = notifyRepository.save(datanotify!!)
            return result.block().toString()
        }catch (e: Exception){
            return e.toString()
        }
    }

    override fun deleteNotify(notifyId: String): String {
        try {
            val datalist = notifyRepository.findAllByPartyId(notifyId)
            datalist.subscribe(
                {data -> System.out.println(data)}
            )
            val data = datalist.collectList().block()!!
            System.out.println(data)
            for (entity in data){
                notifyRepository.delete(entity)
            }
            return data.toString()
        }catch (e: Exception){
            return e.toString()
        }
    }

    override fun findNotifyById(notifyId: String): Mono<NotifyEntity> {
        return notifyRepository.findById(notifyId)
    }

}