package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.entity.PartyEntity
import com.tungty.tungtyservice.service.implement.partyServiceImplement.PartyServiceImp
import com.tungty.tungtyservice.service.partyService.PartyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

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
//    @GetMapping("/{id}")
//    fun getParty(@PathVariable id: String): ResponseEntity<Party?> {
//        val party = partyService.getPartyById(id)
//        return if (party != null) {
//            ResponseEntity(party, HttpStatus.OK)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

    @GetMapping()
    fun getAllParties(): ResponseEntity<Flux<PartyEntity>> {
        println("get all party")
        val parties = partyServiceImp.getAllParties()
        return ResponseEntity(parties, HttpStatus.OK)
    }
//
//    @PutMapping("/{id}")
//    fun updateParty(@PathVariable id: String, @RequestBody updatedParty: Party): ResponseEntity<Party?> {
//        val party = partyService.updateParty(id, updatedParty)
//        return if (party != null) {
//            ResponseEntity(party, HttpStatus.OK)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteParty(@PathVariable id: String): ResponseEntity<Unit> {
//        val success = partyService.deleteParty(id)
//        return if (success) {
//            ResponseEntity(HttpStatus.NO_CONTENT)
//        } else {
//            ResponseEntity(HttpStatus.NOT_FOUND)
//        }
//    }

}