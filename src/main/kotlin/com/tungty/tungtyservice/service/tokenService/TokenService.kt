package com.tungty.tungtyservice.service.tokenService

import com.tungty.tungtyservice.entity.UserEntity
import com.tungty.tungtyservice.repository.user.UserRepository
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService(
        private val jwtDecoder : JwtDecoder,
        private val jwtEncoder: JwtEncoder,
        private val userRepository: UserRepository
) {
    fun createToken(user: UserEntity): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(180L, ChronoUnit.DAYS))
                .subject(user.username)
                .claim("userId", user.userId)
                .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): UserEntity? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as String
            userRepository.findById(userId).block()
        }catch (e: Exception){
            null
        }
    }
}