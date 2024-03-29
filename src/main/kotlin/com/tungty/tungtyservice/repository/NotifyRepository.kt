package com.tungty.tungtyservice.repository

import com.tungty.tungtyservice.entity.NotifyEntity
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface NotifyRepository: ReactiveMongoRepository<NotifyEntity, String> {
    @Query("{'userId' :  ?0}")
    fun findAllByUserId(userId: String): Flux<NotifyEntity>

    @Query("{'partyId' :  ?0}")
    fun findAllByPartyId(partyId: String): Flux<NotifyEntity>
}