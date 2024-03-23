package com.tungty.tungtyservice.repository


import com.tungty.tungtyservice.entity.MessageEntity
import com.tungty.tungtyservice.entity.NotifyEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository
interface MessageRepository: ReactiveMongoRepository<MessageEntity, String> {
    // ...

    @Query("{'partyId' :  ?0}")
    fun findAllByPartyId(partyId: String): Flux<MessageEntity>

}