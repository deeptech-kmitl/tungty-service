package com.tungty.tungtyservice.controller

import com.tungty.tungtyservice.DTO.ReqLoginDTO
import com.tungty.tungtyservice.DTO.ResponseLoginDTO
import com.tungty.tungtyservice.repository.user.UserRepository
import com.tungty.tungtyservice.service.tokenService.TokenService
import com.tungty.tungtyservice.service.userService.HashService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
        private val hashService: HashService,
        private val tokenService: TokenService,
        private val userRepository: UserRepository
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: ReqLoginDTO): ResponseLoginDTO {
        val user = userRepository.findByUsername(payload.username).block() ?: throw error("Login failed")

        if (!hashService.checkBcrypt(payload.password, user.password)){
            throw error("Login failed")
        }
        return ResponseLoginDTO(
                accessToken = tokenService.createToken(user),
                userId = user.userId,
                username = user.username
        )
    }
}