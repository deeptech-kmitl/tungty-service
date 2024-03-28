package com.tungty.tungtyservice.service.implement

import com.tungty.tungtyservice.DTO.chat.ReqMemberListDTO
import com.tungty.tungtyservice.DTO.chat.ReqMessageDTO
import com.tungty.tungtyservice.DTO.chat.ResponseFindProfileImgDTO
import com.tungty.tungtyservice.entity.MessageEntity
import com.tungty.tungtyservice.entity.PartyEntity
import com.tungty.tungtyservice.repository.MessageRepository
import com.tungty.tungtyservice.repository.party.PartyRepository
import com.tungty.tungtyservice.repository.user.UserRepository
import com.tungty.tungtyservice.service.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.util.*

@Service
class ChatServiceImp: ChatService {
    @Autowired
    lateinit var messageRepository: MessageRepository

    @Autowired
    lateinit var partyRepository: PartyRepository

    @Autowired
    lateinit var userRepository: UserRepository
    override fun findAllChatByUserId(userId :String): Flux<PartyEntity> {
        try {
            val allParty = partyRepository.findAll().filter({
                    party -> userId in party.memberList
            })
            return  allParty
        }catch (e: Exception){
            System.out.println(e.toString())
            throw e
        }
    }

    override fun addMessage(message: ReqMessageDTO): String {
        try {
//            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
//            val current = LocalDateTime.now().format(formatter)
            val addData = MessageEntity(
                _id = UUID.randomUUID().toString(),  // Generate new ID
                partyId = message.partyId,
                userId = message.userId,
                message = message.message,
                appointmentDate = message.appointmentDate,
                username = message.username,
                profileImg = message.profileImg
            )
            System.out.println(addData)
//            val partyId: String,
//            val userId: String,
//            val content: String,
//            val createDate: String
            val result = messageRepository.save(addData)
            return result.block().toString()
        }catch (e: Exception){
            System.out.println(e.toString())
            throw e
        }
    }

    override fun findAllMessage(partyId: String): Flux<MessageEntity> {
        try {
            return messageRepository.findAllByPartyId(partyId)
        }catch (e: Exception){
            System.out.println(e.toString())
            throw e
        }
    }

    override fun findProfileImges(memberList: ArrayList<String>): ArrayList<ResponseFindProfileImgDTO> {
        try{
            val datalist = ArrayList<ResponseFindProfileImgDTO>()
            for (i in memberList){
                val setdata = ResponseFindProfileImgDTO(
                    username = i,
                    profileImg = userRepository.findByUsername(i)?.block()!!.profileImg
                )
                datalist.add(setdata)
            }
            return datalist
        }catch (e: Exception){
            throw e
        }
    }

}