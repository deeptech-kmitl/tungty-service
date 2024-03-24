package com.tungty.tungtyservice.service.implement.userServiceImplement
import com.tungty.tungtyservice.DTO.ReqEditProfileDTO
import com.tungty.tungtyservice.DTO.ReqRegisterDTO
import com.tungty.tungtyservice.entity.UserEntity
import com.tungty.tungtyservice.repository.user.UserRepository
import com.tungty.tungtyservice.service.userService.HashService
import com.tungty.tungtyservice.service.userService.userService
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class UserServiceImp : userService {

    @Autowired
    lateinit var userRepository : UserRepository
    lateinit var encoder: HashService

    @PostConstruct
    private fun init() {
        encoder = HashService() // Initialize the encoder here
    }
    override fun createUser(reqRegisterDTO: ReqRegisterDTO): String {
        try {
            val id = UUID.randomUUID().toString()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)

            val hashedPassword = encoder.hashBCrypt(reqRegisterDTO.password)

            val user = UserEntity(
                    userId = id,
                    name = reqRegisterDTO.name,
                    surname = reqRegisterDTO.surname,
                    username = reqRegisterDTO.username,
                    password = hashedPassword,
                    studentId = reqRegisterDTO.studentId,
                    faculty = reqRegisterDTO.faculty,
                    field = reqRegisterDTO.field,
                    year = reqRegisterDTO.year,
                    aboutMe = null,
                    profileImg = reqRegisterDTO.profileImg,
                    createDateTime = current.toString(),
                    updateDateTime = current.toString()
            )
            val result = userRepository.save(user)

            return result.block()?.toString() ?: "fail"

        } catch (error : Exception) {
            return error.toString()
        }
    }

    override fun findUserById(userId: String): Mono<UserEntity?> {
        return userRepository.findById(userId)
    }
    override fun findUserByUsername(username: String): Mono<UserEntity> {
        return userRepository.findByUsername(username)
    }

    override fun getAllUsers(): Flux<UserEntity> {
        return userRepository.findAll()
    }

    override fun editUsers(reqEditProfileDTO: ReqEditProfileDTO): String {
        try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val current = LocalDateTime.now().format(formatter)
            // Fetch the existing party entity from the database
            val userId = reqEditProfileDTO.userId

            val hashedPassword = encoder.hashBCrypt(reqEditProfileDTO.password)

            var existingUserMono: Mono<UserEntity> = userRepository.findById(userId)

            return existingUserMono.flatMap { existingUser ->
                // Update only the attributes that need to be changed
                existingUser.name = reqEditProfileDTO.name
                existingUser.surname = reqEditProfileDTO.surname
                existingUser.password = hashedPassword
                existingUser.studentId = reqEditProfileDTO.studentId
                existingUser.faculty = reqEditProfileDTO.faculty
                existingUser.field = reqEditProfileDTO.field
                existingUser.year = reqEditProfileDTO.year
                existingUser.aboutMe = reqEditProfileDTO.aboutMe
                existingUser.profileImg = reqEditProfileDTO.profileImg
                existingUser.updateDateTime = current.toString()

                // Save the updated user entity back to the database
                userRepository.save(existingUser)
            }.block()?.toString() ?: "fail"
        } catch (error: Exception) {
            return error.toString()
        }
    }
}