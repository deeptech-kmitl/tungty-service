package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.ReqRegisterDTO
import com.tungty.tungtyservice.DTO.ResponseRegisterDTO
import com.tungty.tungtyservice.DTO.ReqEditProfileDTO
import com.tungty.tungtyservice.DTO.ResponseGetMyProfileDTO
import com.tungty.tungtyservice.service.implement.userServiceImplement.UserServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userServiceImp: UserServiceImp

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String) : ResponseEntity<ResponseGetMyProfileDTO> {
        val userMono = userServiceImp.getUserById(id)
        val userEntity = userMono.block()
        return if (userEntity != null) {
            val responseDTO = ResponseGetMyProfileDTO(
                    userId = userEntity.userId,
                    username = userEntity.username,
                    name = userEntity.name,
                    surname = userEntity.surname,
                    faculty = userEntity.faculty,
                    field = userEntity.field,
                    year = userEntity.year.toString()
            )
            ResponseEntity(responseDTO, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping
    fun getAllUsers() : ResponseEntity<Flux<ResponseGetMyProfileDTO>> {
        println("Get all user.")
        val users = userServiceImp.getAllUsers()
                .map { user -> ResponseGetMyProfileDTO(
                        user.userId,
                        user.username,
                        user.name,
                        user.surname,
                        user.faculty,
                        user.field,
                        user.year.toString()) }
        return ResponseEntity(users, HttpStatus.OK)
    }

    @PostMapping("register")
    fun createUser(@RequestBody reqRegisterDTO: ReqRegisterDTO): ResponseEntity<ResponseRegisterDTO> {
        val result = userServiceImp.createUser(reqRegisterDTO)
        return ResponseEntity(ResponseRegisterDTO(errorMessage = result), HttpStatus.OK)
    }
    @PutMapping("edit_profile")
    fun editProfile(@RequestBody reqEditProfileDTO: ReqEditProfileDTO) : String{
        println("Edit Profile By" + reqEditProfileDTO.userId)
        return userServiceImp.editUsers(reqEditProfileDTO)
    }
}