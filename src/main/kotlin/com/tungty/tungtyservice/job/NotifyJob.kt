package com.tungty.tungtyservice.job

import com.tungty.tungtyservice.DTO.notify.ReqAddNotifyDTO
import com.tungty.tungtyservice.entity.NotifyEntity
import com.tungty.tungtyservice.repository.NotifyRepository
import com.tungty.tungtyservice.service.NotifyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.TaskScheduler
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.text.SimpleDateFormat
import java.util.*
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@Service
class NotifyJob() {

    @Autowired
lateinit var notifyRepository: NotifyRepository
    fun getCurrentTimeWithOffset(): String {
        val currentLocale = Locale.getDefault() // Get user's locale (optional)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", currentLocale) // Adjust format if necessary
        dateFormat.timeZone = TimeZone.getTimeZone("Asia/Bangkok") // Set time zone offset (+07:00 for Bangkok)
        return dateFormat.format(Date())
    }
    fun setStatus(data: NotifyEntity): NotifyEntity{
        data.status = "Unread"
        return data
    }
    @Scheduled(cron = "0 * * ? * *")
    fun checkTimeStartParty() {
        System.out.println("Check !");
        try {
            val datasnoti = notifyRepository.findAll()
            System.out.println(getCurrentTimeWithOffset())
            val fildatsnoti = datasnoti.filter({ele -> ele.appointmentTime == getCurrentTimeWithOffset()}).map {
                setStatus(it)
            }.collectList().block()!!
            if (fildatsnoti != null) {
                notifyRepository.saveAll(fildatsnoti)
                    .subscribe { result ->
                        System.out.println(result)
                    }
            }

        } catch (e: Exception) {
            System.out.println("Error: $e")
        }
    }
}
