package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.*
import com.tungty.tungtyservice.service.implement.userServiceImplement.UserServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userServiceImp: UserServiceImp

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: String): ResponseEntity<ResponseGetMyProfileDTO> {
        val userMono = userServiceImp.findUserById(id)
        val userEntity = userMono.block()
        return if (userEntity != null) {
            val responseDTO = ResponseGetMyProfileDTO(
                    userId = userEntity.userId,
                    username = userEntity.username,
                    studentId = userEntity.studentId,
                    name = userEntity.name,
                    surname = userEntity.surname,
                    faculty = userEntity.faculty,
                    field = userEntity.field,
                    year = userEntity.year.toString(),
                    aboutMe = userEntity.aboutMe
            )
            ResponseEntity(responseDTO, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/username/{username}")
    fun findUserByUsername(@PathVariable username: String): ResponseEntity<ResponseGetMyProfileDTO> {
        val userMono = userServiceImp.findUserByUsername(username)
        val userEntity = userMono.block()
        return if (userEntity != null) {
            val responseDTO = ResponseGetMyProfileDTO(
                    userId = userEntity.userId,
                    username = userEntity.username,
                    studentId = userEntity.studentId,
                    name = userEntity.name,
                    surname = userEntity.surname,
                    faculty = userEntity.faculty,
                    field = userEntity.field,
                    year = userEntity.year.toString(),
                    aboutMe = userEntity.aboutMe
            )
            ResponseEntity(responseDTO, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping
    fun getAllUsers(authentication: Authentication): ResponseEntity<Flux<ResponseGetMyProfileDTO>> {
        println("Get all user.")
        val users = userServiceImp.getAllUsers()
                .map { user ->
                    ResponseGetMyProfileDTO(
                            user.userId,
                            user.username,
                            user.studentId,
                            user.name,
                            user.surname,
                            user.faculty,
                            user.field,
                            user.year.toString(),
                            user.aboutMe)
                }
        return ResponseEntity(users, HttpStatus.OK)
    }

    @PostMapping("register")
    fun createUser(@RequestBody reqRegisterDTO: ReqRegisterDTO): ResponseEntity<ResponseRegisterDTO> {
        val result = userServiceImp.createUser(reqRegisterDTO)
        return if (result.startsWith("Error")) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseRegisterDTO(errorMessage = result))
        } else {
            ResponseEntity.ok(ResponseRegisterDTO())
        }
    }

    @PutMapping("edit_profile")
    fun editProfile(@RequestBody reqEditProfileDTO: ReqEditProfileDTO): String {
        println("Edit Profile By" + reqEditProfileDTO.userId)
        return userServiceImp.editUsers(reqEditProfileDTO)
    }
}