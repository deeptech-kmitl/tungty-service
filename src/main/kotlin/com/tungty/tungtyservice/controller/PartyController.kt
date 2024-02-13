package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.ReqCreatePartyDTO
import com.tungty.tungtyservice.DTO.ReqEditPartyDTO
import com.tungty.tungtyservice.entity.PartyEntity
import com.tungty.tungtyservice.service.implement.partyServiceImplement.PartyServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/party")
class PartyController {

    //gencode
    @Autowired
    lateinit var partyServiceImp: PartyServiceImp

    //GenPartycode
    @PostMapping("genCode/{partyId}")
    fun genPartyCode(@PathVariable("partyId") partyId: String): String {
        println("genPartyCode at controller")
        return partyServiceImp.genPartyCode(partyId)
    }
    //getPartycode
    @GetMapping("partyCode/{partyId}")
    fun getPartyCode(@PathVariable partyId: String): String {
        println("Get PartyCode at controller")
        return "Get PartyCode at controller"
//        return partyCodeService.getPartyCode(partyId)
    }


    //CRUD party
    @GetMapping("/{id}")
    fun getPartyById(@PathVariable id: String): ResponseEntity<Mono<PartyEntity>> {
        val party = partyServiceImp.getPartyById(id)
        return if (party != null) {
            ResponseEntity(party, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping()
    fun getAllParties(): ResponseEntity<Flux<PartyEntity>> {
        println("get all party")
        val parties = partyServiceImp.getAllParties()
        return ResponseEntity(parties, HttpStatus.OK)
    }
    //Create party
    @PostMapping()
    fun createParty(@RequestBody reqCreatePartyDTO:ReqCreatePartyDTO) : String {
        return partyServiceImp.createParty(reqCreatePartyDTO)
    }

//Update party
    @PutMapping()
    fun editParty(@RequestBody reqEditPartyDTO: ReqEditPartyDTO): String {
        println("edit party" + reqEditPartyDTO.partyId)
//        val party = partyService.updateParty(id, updatedParty)
        return partyServiceImp.editParty(reqEditPartyDTO)

//        return if (party != null) {
//            ResponseEntity(party, HttpStatus.OK)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
    }
//
//    @DeleteMapping("/{id}")
//    fun deleteParty(@PathVariable id: String): ResponseEntity<Unit> {
//        val success = partyServiceImp.deleteParty(id)
//        return if (success) {
//            ResponseEntity(HttpStatus.NO_CONTENT)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

}